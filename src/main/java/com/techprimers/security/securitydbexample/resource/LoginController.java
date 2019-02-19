package com.techprimers.security.securitydbexample.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techprimers.security.securitydbexample.model.Users;
import com.techprimers.security.securitydbexample.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequestMapping("/rest")
@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

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

    private String createCouchbaseUser(Users user)
    {
        String url = "http://35.235.100.173:4985/homecareplus/_user/";

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
        String postUrl = "http://35.235.100.173:4985/homecareplus/_session";
        String deleteUrl = "http://35.235.100.173:4985/homecareplus/_user/" + user.getUsername() + "/_session";
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
