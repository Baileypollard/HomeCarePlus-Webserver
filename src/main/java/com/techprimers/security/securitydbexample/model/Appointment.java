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

    private String appointment_id;
    private String address;
    private String comment;
    private String end_time;
    private String start_time;
    private String gender;
    private String last_name;
    private String phone_number;
    private String punched_in_time;
    private String punched_out_time;
    private String status;
    private String date;
    private String employee_id;
    private String first_name;

    public Appointment()
    {

    }

    public Appointment(String first_name, String appointment_id, String address, String comment, String end_time, String start_time, String gender, String last_name, String phone_number, String punched_in_time, String punched_out_time, String status, String date, String employee_id)
    {
        this.appointment_id = appointment_id;
        this.address = address;
        this.comment = comment;
        this.end_time = end_time;
        this.start_time = start_time;
        this.gender = gender;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.punched_in_time = punched_in_time;
        this.punched_out_time = punched_out_time;
        this.status = status;
        this.date = date;
        this.employee_id = employee_id;
        this.first_name = first_name;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getAppointment_id()
    {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id)
    {
        this.appointment_id = appointment_id;
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

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(String end_time)
    {
        this.end_time = end_time;
    }

    public String getStart_time()
    {
        return start_time;
    }

    public void setStart_time(String start_time)
    {
        this.start_time = start_time;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getDocumentID()
    {
        return _id;
    }

    public void setDocumentID(String documentID)
    {
        this._id = documentID;
    }

    public String getPunched_in_time()
    {
        return punched_in_time;
    }

    public void setPunched_in_time(String punched_in_time)
    {
        this.punched_in_time = punched_in_time;
    }

    public String getPunched_out_time()
    {
        return punched_out_time;
    }

    public void setPunched_out_time(String punched_out_time)
    {
        this.punched_out_time = punched_out_time;
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

    public String getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(String employee_id)
    {
        this.employee_id = employee_id;
    }

    public String getKey()
    {
        return getEmployee_id() + "." + getDate();
    }
}
