package com.techprimers.security.securitydbexample.views;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateAppointmentWindow extends Window
{
    public CreateAppointmentWindow(EmployeeService employeeService, ClientService clientService, AppointmentService appointmentService)
    {
        super("New Appointment");
        VerticalLayout layout = new CreateAppointmentView(employeeService, clientService, appointmentService);
        layout.setSizeFull();
        setContent(layout);
        getContent().setSizeUndefined();
        center();
    }
}
