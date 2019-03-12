package com.techprimers.security.securitydbexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class AppointmentType
{
    @JsonProperty("name")
    public String name;

    @JsonProperty("abbreviation")
    public String abbreviation;

    public AppointmentType(String name, String abbreviation)
    {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString()
    {
        return getAbbreviation() + " - " + getName();
    }
}
