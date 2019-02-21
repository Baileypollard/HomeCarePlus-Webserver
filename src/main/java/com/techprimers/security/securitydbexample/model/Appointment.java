package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Id;

import java.util.UUID;

@Document
public class Appointment
{
    @Id
    private String _id = UUID.randomUUID().toString();

    @Field("appointment_id")
    private String appointmentId;
    @Field("address")
    private String address;
    @Field("comment")
    private String comment;
    @Field("end_time")
    private String endTime;
    @Field("start_time")
    private String startTime;
    @Field("gender")
    private String gender;
    @Field("last_name")
    private String lastName;
    @Field("phone_number")
    private String phoneNumber;
    @Field("punched_in_time")
    private String punchedInTime;
    @Field("punched_out_time")
    private String punchedOutTime;
    @Field("status")
    private String status;
    @Field("date")
    private String date;
    @Field("employee_id")
    private String employeeId;
    @Field("first_name")
    private String firstName;

    public Appointment()
    {

    }

    public Appointment(String firstName, String appointmentId, String address, String comment, String endTime, String startTime, String gender, String lastName, String phoneNumber, String punchedInTime, String punchedOutTime, String status, String date, String employeeId)
    {
        this.appointmentId = appointmentId;
        this.address = address;
        this.comment = comment;
        this.endTime = endTime;
        this.startTime = startTime;
        this.gender = gender;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.punchedInTime = punchedInTime;
        this.punchedOutTime = punchedOutTime;
        this.status = status;
        this.date = date;
        this.employeeId = employeeId;
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getAppointmentId()
    {
        return appointmentId;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getComment()
    {
        return comment;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getGender()
    {
        return gender;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String last_name)
    {
        this.lastName = last_name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number)
    {
        this.phoneNumber = phone_number;
    }

    public String getDocumentID()
    {
        return _id;
    }

    public void setDocumentID(String documentID)
    {
        this._id = documentID;
    }

    public String getPunchedInTime()
    {
        return punchedInTime;
    }

    public void setPunchedInTime(String punched_in_time)
    {
        this.punchedInTime = punched_in_time;
    }

    public String getPunchedOutTime()
    {
        return punchedOutTime;
    }

    public void setPunchedOutTime(String punched_out_time)
    {
        this.punchedOutTime = punched_out_time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employee_id)
    {
        this.employeeId = employee_id;
    }

    public String getKey()
    {
        return getEmployeeId() + "." + getDate();
    }
}
