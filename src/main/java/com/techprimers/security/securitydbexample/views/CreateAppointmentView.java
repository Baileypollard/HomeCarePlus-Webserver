package com.techprimers.security.securitydbexample.views;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.model.Employee;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.UUID;

public class CreateAppointmentView extends VerticalLayout
{
    private ComboBox<Client> clientsCB;
    private ComboBox<Employee> employeesCB;
    private TextField startTime;
    private TextField endTime;
    private DateField dateField;

    public CreateAppointmentView(EmployeeService employeeService, ClientService clientService,
                                 AppointmentService appointmentService)
    {
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
        dateField.setWidth("-1px");
        dateField.setDateFormat("yyyy-MM-dd");

        startTime = new TextField("Start Time: ");
        endTime = new TextField("End Time: ");

        Button createButton = new Button("Create Appointment");
        createButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        createButton.addClickListener(clickEvent -> {
                    Appointment appointment = createNewAppointment();
                    appointmentService.createAppointment(appointment);
                });

        layout.addComponents(clientsCB, employeesCB, dateField, startTime, endTime, createButton);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    private Appointment createNewAppointment()
    {
        Client selectedClient = clientsCB.getSelectedItem().get();
        Employee selectedEmployee = employeesCB.getSelectedItem().get();

        String firstName = selectedClient.getFirst_name();
        String lastName = selectedClient.getLast_name();
        String address = selectedClient.getAddress();
        String startTime = this.startTime.getValue();
        String endTime = this.endTime.getValue();
        String date = dateField.getValue().toString();
        String gender = selectedClient.getGender();
        String phoneNumber = selectedClient.getPhone_number();
        String employeeId = selectedEmployee.getEmployee_id();

        return new Appointment(firstName, UUID.randomUUID().toString(), address, "", endTime, startTime, gender,
                lastName, phoneNumber, "", "", "NEW", date, employeeId);

    }
}
