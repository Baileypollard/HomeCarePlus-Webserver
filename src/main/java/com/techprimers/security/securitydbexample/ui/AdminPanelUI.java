package com.techprimers.security.securitydbexample.ui;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.repository.AppointmentRepository;
import com.techprimers.security.securitydbexample.repository.ClientRepository;
import com.techprimers.security.securitydbexample.repository.EmployeeRepository;
import com.techprimers.security.securitydbexample.service.AppointmentServiceImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import views.CreateAppointmentWindow;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@SpringUI(path = "/admin/panel")
@Title("Admin Panel")
@Theme("valo")
public class AdminPanelUI extends UI
{
    private VerticalLayout root;
    private Grid<Appointment> appointmentGrid;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        root = new VerticalLayout();
        root.setWidth("100%");
        root.setHeight("100%");
        root.setSpacing(true);

        setContent(root);
        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newAppointmentButton = new Button("New Appointment", VaadinIcons.PLUS_CIRCLE);
        newAppointmentButton.addClickListener(clickEvent -> {
                    Collection<Window> windows = getWindows();
                    if (getWindows().size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    addWindow(new CreateAppointmentWindow(employeeRepository, clientRepository, appointmentService));
                }
        );
        newAppointmentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.addClickListener(clickEvent -> {
            Set<Appointment> selectedItems = appointmentGrid.getSelectedItems();
            selectedItems.forEach(appointment -> appointmentService.removeAppointmentByAppointmentId(appointment.getAppointment_id()));
            appointmentGrid.setItems(appointmentService.findAll());
        });
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);

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

        root.addComponents(buttonLayout, appointmentGrid);
        root.setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        root.setExpandRatio(appointmentGrid, 1.0f);
    }


}
