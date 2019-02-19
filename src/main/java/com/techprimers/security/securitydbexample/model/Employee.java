package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

@Document
public class Employee
{
    @Id
    private String id = UUID.randomUUID().toString();

    private String employee_id;

    private String first_name;
    private String last_name;
    private String gender;
    private String phone_number;
    private String address;

    public Employee(String employee_id, String first_name, String last_name, String gender, String phone_number, String address)
    {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getEmployeeId()
    {
        return employee_id;
    }

    public String getFirstName()
    {
        return first_name;
    }

    public String getLastName()
    {
        return last_name;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return getFirstName() + " " + getLastName();
    }
}
