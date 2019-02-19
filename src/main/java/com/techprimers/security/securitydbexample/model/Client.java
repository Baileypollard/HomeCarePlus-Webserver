package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Client
{
    @Id
    @Field(value = "client_id")
    private String client_id;

    @Field(value = "first_name")
    private String first_name;

    @Field(value = "last_name")
    private String last_name;

    @Field(value = "address")
    private String address;

    @Field(value = "gender")
    private String gender;

    @Field(value = "phone_number")
    private String phone_number;

    public Client(String client_id, String first_name, String last_name, String address, String gender, String phone_number)
    {
        this.client_id = client_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.gender = gender;
        this.phone_number = phone_number;
    }

    public String getClientId()
    {
        return client_id;
    }

    public String getFirstName()
    {
        return first_name;
    }

    public String getLastName()
    {
        return last_name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getPhoneNumber()
    {
        return phone_number;
    }

    @Override
    public String toString()
    {
        return getFirstName() + " " + getLastName();
    }
}
