package com.techprimers.security.securitydbexample.interfaces;

import com.techprimers.security.securitydbexample.model.Appointment;

import java.util.List;

public interface AppointmentService
{
    List<Appointment> findAll();

    void removeAppointmentByAppointmentId(String appointmentId);

    Appointment createAppointment(Appointment appointment);

    void updateAppointmentStatus(Appointment appointment);
}
