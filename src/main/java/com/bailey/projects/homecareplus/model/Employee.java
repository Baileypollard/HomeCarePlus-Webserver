package com.bailey.projects.homecareplus.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

@Document
public class Employee
{
    @Id
    private String id = UUID.randomUUID().toString();

    @Field("employee_id")
    private String employeeId;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("gender")
    private String gender;

    @Field("phone_number")
    private String phoneNumber;

    @Field("address")
    private String address;

    public Employee(String employeeId, String firstName, String lastName, String gender, String phoneNumber, String address)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
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
        return phoneNumber;
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
