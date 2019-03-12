package com.techprimers.security.securitydbexample.resource;

import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.model.Users;
import com.techprimers.security.securitydbexample.service.AppointmentServiceImpl;
import com.techprimers.security.securitydbexample.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@RequestMapping("/rest")
@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/secured/login")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String login(@RequestBody Users user)
    {
        createCouchbaseUser(user);
        return createCouchbaseSession(user);
    }

    @PostMapping("/secured/verify")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void verify(@RequestBody Appointment appointment)
    {
        if (appointment.getPunchedOutLoc() == null || appointment.getPunchedInLoc() == null)
        {
            System.out.println("ERROR, NULL");
            return;
        }

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyA0HS0GBYbxEbdXhzsP7sUnk2MDT7j3XFw")
                .build();
        try
        {
            GeocodingResult[] results =  GeocodingApi.geocode(context, appointment.getAddress()).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject geometryObject = JsonObject.fromJson(gson.toJson(results[0].geometry));
            JsonObject locationObj = geometryObject.getObject("location");

            LatLng addressPoint = new LatLng(locationObj.getDouble("lat"), locationObj.getDouble("lng"));
            LatLng punchedInPoint = new LatLng(appointment.getPunchedInLoc().get("lat"), appointment.getPunchedInLoc().get("lng"));
            LatLng punchedOutPoint = new LatLng(appointment.getPunchedOutLoc().get("lat"), appointment.getPunchedOutLoc().get("lng"));

            DistanceMatrixApiRequest request = new DistanceMatrixApiRequest(context);
            DistanceMatrix trix = request.origins(punchedInPoint, punchedOutPoint).
                    destinations(addressPoint).mode(TravelMode.DRIVING).
                    await();

            long punchedInDistanceMeters = trix.rows[0].elements[0].distance.inMeters;
            long punchedOutDistanceMeters = trix.rows[1].elements[0].distance.inMeters;

            //If the distance is greater than 200m, then decline the appointment, otherwise verify it
            if (punchedInDistanceMeters > 200 || punchedOutDistanceMeters > 200)
            {
                appointment.setStatus("DECLINED");
            }
            else
            {
                appointment.setStatus("VERIFIED");
            }
            appointmentService.updateAppointmentStatus(appointment);
        }
        catch (IOException | ApiException  | InterruptedException e)
        {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private String createCouchbaseUser(Users user)
    {
        String url = "http://35.235.103.244:4985/homecareplus/_user/";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode mainNode = objectMapper.createObjectNode();


        mainNode.put("name", user.getUsername());
        mainNode.put("password", user.getPassword());

        ArrayNode arrayNode = objectMapper.createArrayNode();

        arrayNode.add("*");

        mainNode.putPOJO("admin_channels", arrayNode);


        HttpEntity<?> entity = new HttpEntity<>(mainNode, headers);

        try
        {
            System.out.println("Creating User: " + user.getUsername());
            return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        }
        catch (HttpClientErrorException e)
        {
            if (e.getStatusCode().equals(HttpStatus.CONFLICT))
            {
                System.out.println("User already exists...");
                return "User already exists...";
            }
        }
        return null;
    }

    private String createCouchbaseSession(Users user)
    {
        String postUrl = "http://35.235.103.244:4985/homecareplus/_session";
        String deleteUrl = "http://35.235.103.244:4985/homecareplus/_user/" + user.getUsername() + "/_session";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode sessionNode = objectMapper.createObjectNode();
        sessionNode.put("name", user.getUsername());
        sessionNode.put("ttl", 3600);

        HttpEntity<?> entity = new HttpEntity<>(sessionNode, headers);
        //Delete all current
        restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class);

        return restTemplate.exchange(postUrl, HttpMethod.POST, entity, String.class).getBody();
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String register(@RequestBody Users user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDetailsService.saveUser(user);
        return "Successfully Registered user " + user.getId();
    }
}
