package com.techprimers.security.securitydbexample.ui.views;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.model.AppointmentType;
import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.model.Employee;
import com.techprimers.security.securitydbexample.repository.AppointmentTypeRepository;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateAppointmentView extends HorizontalLayout
{
    private ComboBox<Client> clientsCB;
    private ComboBox<AppointmentType> appointmentTypeComboBox;
    private ComboBox<Employee> employeesCB;
    private DateTimeField startTime;
    private DateTimeField endTime;
    private DateField dateField;
    private TextArea appointmentDescription;
    private List<AppointmentType> appointmentTypes;

    public CreateAppointmentView(EmployeeService employeeService, ClientService clientService,
                                 AppointmentService appointmentService, AppointmentTypeRepository repository)
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

        appointmentTypeComboBox = new ComboBox<>("Appointment Type: ");
        appointmentTypeComboBox.setItems(repository.getAllAppointmentTypes());
        appointmentTypeComboBox.setEmptySelectionAllowed(false);

        dateField = new DateField("Date: ");
        dateField.setDateFormat("yyyy-MM-dd");

        startTime = new DateTimeField("Start Time: ");
        startTime.setStyleName("time-only");
        startTime.setDateFormat("h:mm a");
        startTime.setLocale(Locale.CANADA);

        endTime = new DateTimeField("End Time: ");
        endTime.setStyleName("time-only");
        endTime.setDateFormat("h:mm a");
        endTime.setLocale(Locale.CANADA);

        appointmentDescription = new TextArea("Appointment Description: ");

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

        layout.addComponents(employeesCB, clientsCB, dateField, startTime, endTime, appointmentTypeComboBox, appointmentDescription, createButton);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    private Appointment createNewAppointment()
    {
        try
        {
            Client selectedClient = clientsCB.getSelectedItem().get();
            Employee selectedEmployee = employeesCB.getSelectedItem().get();
            AppointmentType type = appointmentTypeComboBox.getSelectedItem().get();

            String firstName = selectedClient.getFirstName();
            String lastName = selectedClient.getLastName();
            String address = selectedClient.getAddress();
            String date = dateField.getValue().toString();
            String gender = selectedClient.getGender();
            String phoneNumber = selectedClient.getPhoneNumber();
            String employeeId = selectedEmployee.getEmployeeId();
            String clientId = selectedClient.getClientId();
            String description = appointmentDescription.getValue();

            //Convert to ms, since that is what is expected
            long startTime = this.dateField.getValue().atTime(this.startTime.getValue().getHour(),
                    this.startTime.getValue().getMinute()).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000;

            long endTime = this.dateField.getValue().atTime(this.endTime.getValue().getHour(),
                    this.endTime.getValue().getMinute()).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000;

            return new Appointment(UUID.randomUUID().toString(), firstName,  address, "", endTime, startTime, gender,
                    lastName, phoneNumber, "", "", "NEW", date, employeeId, clientId, description, type);
        }
        catch (NoSuchElementException e)
        {
            Notification.show("Please ensure there are no empty fields", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
