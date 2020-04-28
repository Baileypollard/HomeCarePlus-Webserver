package com.bailey.projects.homecareplus.service;

import com.bailey.projects.homecareplus.interfaces.AppointmentService;
import com.bailey.projects.homecareplus.repository.AppointmentRepository;
import com.bailey.projects.homecareplus.utils.DocumentCreator;
import com.bailey.projects.homecareplus.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll()
    {
        return appointmentRepository.findAll();
    }

    @Override
    public void removeAppointmentByAppointmentId(String appointmentId)
    {
        appointmentRepository.removeAppointmentByAppointmentId(appointmentId);
    }

    @Override
    public Appointment createAppointment(Appointment appointment)
    {
        return appointmentRepository.createAppointment(appointment.getKey(), DocumentCreator.createAppointment(appointment),
                DocumentCreator.createAppointmentDocument(appointment));
    }

    @Override
    public void updateAppointmentStatus(Appointment appointment)
    {
        appointmentRepository.updateAppointmentStatus(appointment.getAppointmentId(), appointment.getStatus());
    }
}
