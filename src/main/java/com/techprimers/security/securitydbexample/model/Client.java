package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Client
{
    @Id
    @Field(value = "client_id")
    private String clientId;

    @Field(value = "first_name")
    private String firstName;

    @Field(value = "last_name")
    private String lastName;

    @Field(value = "address")
    private String address;

    @Field(value = "gender")
    private String gender;

    @Field(value = "phone_number")
    private String phoneNumber;

    @Field(value = "additional_information")
    private String additionalInformation;

    public Client(String clientId, String firstName, String lastName,
                  String address, String gender, String phoneNumber, String additionalInformation)
    {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.additionalInformation = additionalInformation;
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalInformation()
    {
        return additionalInformation;
    }

    public String getClientId()
    {
        return clientId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
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
        return phoneNumber;
    }

    @Override
    public String toString()
    {
        return getFirstName() + " " + getLastName();
    }
}
