package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Client
{
    @Id
    private String client_id;
    private String first_name;
    private String last_name;
    private String address;
    private String gender;
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

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
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

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    @Override
    public String toString()
    {
        return getFirst_name() + " " + getLast_name();
    }
}
