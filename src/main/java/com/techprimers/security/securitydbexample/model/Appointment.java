package com.techprimers.security.securitydbexample.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techprimers.security.securitydbexample.utils.DateUtil;
import org.springframework.data.couchbase.core.mapping.Document;
import com.couchbase.client.java.repository.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Map;
import java.util.UUID;

@Document
public class Appointment
{
    @Id
    private String _id = UUID.randomUUID().toString();

    @JsonProperty("appointment_id")
    @Field("appointment_id")
    private String appointmentId;

    @JsonProperty("address")
    @Field("address")
    private String address;

    @JsonProperty("comment")
    @Field("comment")
    private String comment;

    @JsonProperty("end_time")
    @Field("end_time")
    private long endTime;

    @JsonProperty("start_time")
    @Field("start_time")
    private long startTime;

    @JsonProperty("gender")
    @Field("gender")
    private String gender;

    @JsonProperty("last_name")
    @Field("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    @Field("phone_number")
    private String phoneNumber;

    @JsonProperty("punched_in_time")
    @Field("punched_in_time")
    private String punchedInTime;

    @JsonProperty("punched_out_time")
    @Field("punched_out_time")
    private String punchedOutTime;

    @JsonProperty("status")
    @Field("status")
    private String status;

    @JsonProperty("date")
    @Field("date")
    private String date;

    @JsonProperty("employee_id")
    @Field("employee_id")
    private String employeeId;

    @JsonProperty("first_name")
    @Field("first_name")
    private String firstName;

    @JsonProperty("punched_in_loc")
    @Field("punched_in_loc")
    private Map<String, Double> punchedInLoc;

    @JsonProperty("punched_out_loc")
    @Field("punched_out_loc")
    private Map<String, Double> punchedOutLoc;

    public Appointment()
    {
    }

    public Appointment(String firstName, String appointmentId, String address, String comment, long endTime, long startTime, String gender, String lastName, String phoneNumber, String punchedInTime, String punchedOutTime, String status, String date, String employeeId)
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

    public Map<String, Double> getPunchedOutLoc()
    {
        return punchedOutLoc;
    }

    public Map<String, Double> getPunchedInLoc()
    {
        return punchedInLoc;
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

    public long getEndTime()
    {
        return endTime;
    }

    public String getFormattedStartTime()
    {
        return DateUtil.convertMsToTime(startTime);
    }

    public String getFormattedEndTime()
    {
        return DateUtil.convertMsToTime(endTime);
    }

    public long getStartTime()
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
