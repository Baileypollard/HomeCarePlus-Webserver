package com.techprimers.security.securitydbexample.ui.views;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.model.Employee;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateAppointmentView extends VerticalLayout
{
    private ComboBox<Client> clientsCB;
    private ComboBox<Employee> employeesCB;
    private DateTimeField startTime;
    private DateTimeField endTime;
    private DateField dateField;

    public CreateAppointmentView(EmployeeService employeeService, ClientService clientService,
                                 AppointmentService appointmentService)
    {
        setCaption("New Appointment");
        setWidth("100%");
        setHeight("100%");
        setSpacing(true);

        FormLayout layout = new FormLayout();
        clientsCB = new ComboBox<>("Client: ");
        employeesCB = new ComboBox<>("Employee: ");

        employeesCB.setItems(employeeService.findAll());
        employeesCB.setEmptySelectionAllowed(false);

        clientsCB.setItems(clientService.findAll());
        clientsCB.setEmptySelectionAllowed(false);

        dateField = new DateField("Date: ");
        dateField.setDateFormat("yyyy-MM-dd");

        startTime = new DateTimeField("Start Time: ");
        startTime.setStyleName("time-only");
        startTime.setDateFormat("hh:mm a");
        startTime.setLocale(Locale.CANADA);
        startTime.setWidth("-1px");

        endTime = new DateTimeField("End Time: ");
        endTime.setStyleName("time-only");
        endTime.setDateFormat("hh:mm a");
        endTime.setLocale(Locale.CANADA);
        endTime.setWidth("-1px");

        Button createButton = new Button("Create Appointment");
        createButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        createButton.addClickListener(clickEvent -> {
                    Appointment appointment = createNewAppointment();
                    if (appointment != null)
                    {
                        appointmentService.createAppointment(appointment);
                        Collection<Window> windows = getUI().getWindows();
                        windows.forEach(Window::close);
                    }
                });

        layout.addComponents(employeesCB, clientsCB, dateField, startTime, endTime, createButton);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    private Appointment createNewAppointment()
    {
        try
        {
            Client selectedClient = clientsCB.getSelectedItem().get();
            Employee selectedEmployee = employeesCB.getSelectedItem().get();

            String firstName = selectedClient.getFirstName();
            String lastName = selectedClient.getLastName();
            String address = selectedClient.getAddress();
            String startTime = this.startTime.getValue().format(DateTimeFormatter.ofPattern("hh:mm a"));
            String endTime = this.endTime.getValue().format(DateTimeFormatter.ofPattern("hh:mm a"));
            String date = dateField.getValue().toString();
            String gender = selectedClient.getGender();
            String phoneNumber = selectedClient.getPhoneNumber();
            String employeeId = selectedEmployee.getEmployeeId();

            return new Appointment(firstName, UUID.randomUUID().toString(), address, "", endTime, startTime, gender,
                    lastName, phoneNumber, "", "", "NEW", date, employeeId);
        }
        catch (NoSuchElementException e)
        {
            Notification.show("Please ensure there are no empty fields", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
