package com.techprimers.security.securitydbexample.ui;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.techprimers.security.securitydbexample.views.CreateAppointmentWindow;

import java.util.Collection;
import java.util.Set;

public class AppointmentPage extends VerticalLayout implements View
{

    private EmployeeService employeeService;
    private ClientService clientService;
    private AppointmentService appointmentService;
    private Grid<Appointment> appointmentGrid;

    public AppointmentPage()
    {

    }

    public AppointmentPage(EmployeeService employeeService, ClientService clientService, AppointmentService appointmentService)
    {
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        setWidth("100%");
        setHeight("100%");
        setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newAppointmentButton = new Button("New Appointment", VaadinIcons.PLUS_CIRCLE);
        newAppointmentButton.addClickListener(clickEvent -> {
                    Collection<Window> windows = getUI().getWindows();
                    if (windows.size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    getUI().addWindow(new CreateAppointmentWindow(employeeService, clientService,
                            appointmentService));
                }
        );
        newAppointmentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> {
            Set<Appointment> selectedItems = appointmentGrid.getSelectedItems();
            selectedItems.forEach(appointment -> appointmentService.removeAppointmentByAppointmentId(appointment.getAppointment_id()));
            appointmentGrid.setItems(appointmentService.findAll());
        });

        buttonLayout.addComponents(newAppointmentButton, deleteButton);

        appointmentGrid = new Grid<>();
        appointmentGrid.setWidth("100%");
        appointmentGrid.setHeight("100%");

        appointmentGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        appointmentGrid.addColumn(Appointment::getDocumentID).setCaption("DOC ID");
        appointmentGrid.addColumn(Appointment::getAppointment_id).setCaption("APP ID");
        appointmentGrid.addColumn(Appointment::getEmployee_id).setCaption("Employee");
        appointmentGrid.addColumn(Appointment::getFirst_name).setCaption("First Name");
        appointmentGrid.addColumn(Appointment::getLast_name).setCaption("Last Name");
        appointmentGrid.addColumn(Appointment::getAddress).setCaption("Address");
        appointmentGrid.addColumn(Appointment::getStart_time).setCaption("Start Time");
        appointmentGrid.addColumn(Appointment::getEnd_time).setCaption("End Time");
        appointmentGrid.addColumn(Appointment::getDate).setCaption("Date");
        appointmentGrid.addColumn(Appointment::getComment).setCaption("Comment");

        appointmentGrid.setItems(appointmentService.findAll());

        Button logout = new Button("Log Out");
        logout.addClickListener(click -> {
            VaadinService.getCurrentRequest().getWrappedSession().invalidate();
            getUI().getPage().setLocation("/login");
        });
        addComponents(logout, buttonLayout, appointmentGrid);
        setComponentAlignment(logout, Alignment.TOP_RIGHT);
        setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        setExpandRatio(appointmentGrid, 1.0f);
    }

}
