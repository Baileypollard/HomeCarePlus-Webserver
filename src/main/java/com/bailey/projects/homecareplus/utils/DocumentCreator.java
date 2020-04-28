package com.bailey.projects.homecareplus.utils;

import com.bailey.projects.homecareplus.model.Appointment;
import com.bailey.projects.homecareplus.model.Employee;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.bailey.projects.homecareplus.model.Client;

public class DocumentCreator
{
    public static JsonObject createClientDocument(Client client)
    {
        JsonObject document = JsonObject.create();
        document.put("first_name", client.getFirstName());
        document.put("last_name", client.getLastName());
        document.put("address", client.getAddress());
        document.put("phone_number", client.getPhoneNumber());
        document.put("gender", client.getGender());
        document.put("client_id", client.getClientId());
        document.put("additional_information", client.getAdditionalInformation());
        document.put("health_card_number", client.getHealthCardNumber());
        document.put("emergency_contact_name", client.getEmergencyContactName());
        document.put("emergency_phone_number", client.getEmergencyContactPhoneNumber());
        document.put("type", "client");


        return document;
    }

    public static JsonObject createEmployeeDocument(Employee employee)
    {
        JsonObject document = JsonObject.create();

        document.put("first_name", employee.getFirstName());
        document.put("last_name", employee.getLastName());
        document.put("address", employee.getAddress());
        document.put("phone_number", employee.getPhoneNumber());
        document.put("gender", employee.getGender());
        document.put("employee_id", employee.getEmployeeId());
        document.put("type", "employee");

        return document;
    }

    public static JsonObject createAppointment(Appointment appointment)
    {
        JsonObject document = JsonObject.create();

        document.put("first_name", appointment.getFirstName());
        document.put("phone_number", appointment.getPhoneNumber());
        document.put("last_name", appointment.getLastName());
        document.put("address", appointment.getAddress());
        document.put("start_time", appointment.getStartTime());
        document.put("end_time", appointment.getEndTime());
        document.put("appointment_id", appointment.getAppointmentId());
        document.put("status", appointment.getStatus());
        document.put("gender", appointment.getGender());
        document.put("client_id", appointment.getClientId());
        document.put("description", appointment.getDescription());

        JsonObject type = JsonObject.create();
        type.put("name", appointment.getType().getName());
        type.put("abbreviation", appointment.getType().getAbbreviation());

        document.put("type", type);

        return document;
    }

    public static JsonObject createAppointmentDocument(Appointment appointment)
    {
        JsonObject document = JsonObject.create();
        JsonArray schedule = JsonArray.create();
        schedule.add(createAppointment(appointment));

        document.put("date", appointment.getDate());
        document.put("employee_id", appointment.getEmployeeId());
        document.put("type", "appointment");
        document.put("schedule", schedule);

        return document;
    }


}
