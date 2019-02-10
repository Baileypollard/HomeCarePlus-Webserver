package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

@Document
public class Employee
{
    @Id
    private String id = UUID.randomUUID().toString();
    private String first_name;
    private String last_name;
    private String gender;
    private String phone_number;
    private String address;

    public Employee(String first_name, String last_name, String gender, String phone_number, String address)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
