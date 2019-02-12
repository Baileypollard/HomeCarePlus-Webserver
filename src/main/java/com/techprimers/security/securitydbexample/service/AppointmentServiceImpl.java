package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.repository.AppointmentRepository;
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
        return appointmentRepository.createAppointment(appointment.getKey(), appointment.getEmployee_id(), appointment.getDate(),
                appointment.getFirst_name(), appointment.getLast_name(), appointment.getAddress(), appointment.getStart_time(),
                appointment.getEnd_time(), appointment.getAppointment_id(), appointment.getGender(), appointment.getStatus());
    }
}
