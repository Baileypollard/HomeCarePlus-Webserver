package com.bailey.projects.homecareplus.interfaces;

import com.bailey.projects.homecareplus.model.Appointment;

import java.util.List;

public interface AppointmentService
{
    List<Appointment> findAll();

    void removeAppointmentByAppointmentId(String appointmentId);

    Appointment createAppointment(Appointment appointment);

    void updateAppointmentStatus(Appointment appointment);
}
