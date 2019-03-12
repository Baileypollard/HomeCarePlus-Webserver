package com.techprimers.security.securitydbexample.ui.pages;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.repository.AppointmentTypeRepository;
import com.techprimers.security.securitydbexample.ui.views.CreateAppointmentView;
import com.techprimers.security.securitydbexample.ui.views.CreateWindowWithLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Set;


@SpringView(name = "")
public class AppointmentPage extends VerticalLayout implements View
{
    private AppointmentService appointmentService;
    private Grid<Appointment> appointmentGrid;

    public AppointmentPage(EmployeeService employeeService, ClientService clientService, AppointmentService appointmentService, AppointmentTypeRepository repository)
    {
        this.appointmentService = appointmentService;

        setHeight("100%");
        setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newAppointmentButton = new Button("New Appointment", VaadinIcons.PLUS_CIRCLE);
        newAppointmentButton.addClickListener(clickEvent ->
                {
                    Collection<Window> windows = getUI().getWindows();
                    if (windows.size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    VerticalLayout appointmentLayout = new CreateAppointmentView(employeeService, clientService, appointmentService, repository);
                    Window appointmentWindow = new CreateWindowWithLayout(appointmentLayout);
                    getUI().addWindow(appointmentWindow);
                    appointmentWindow.addCloseListener(closeEvent -> refreshGrid());
                }
        );
        newAppointmentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Appointment> selectedItems = appointmentGrid.getSelectedItems();
            selectedItems.forEach(appointment -> appointmentService.removeAppointmentByAppointmentId(appointment.getAppointmentId()));
            refreshGrid();
        });

        buttonLayout.addComponents(newAppointmentButton, deleteButton);

        appointmentGrid = new Grid<>();
        appointmentGrid.setSizeFull();

        appointmentGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        appointmentGrid.addColumn(Appointment::getEmployeeId).setCaption("Employee");
        appointmentGrid.addColumn(Appointment::getFirstName).setCaption("First Name");
        appointmentGrid.addColumn(Appointment::getLastName).setCaption("Last Name");
        appointmentGrid.addColumn(Appointment::getAddress).setCaption("Address");
        appointmentGrid.addColumn(Appointment::getFormattedStartTime).setCaption("Start Time");
        appointmentGrid.addColumn(Appointment::getFormattedEndTime).setCaption("End Time");
        appointmentGrid.addColumn(Appointment::getDate).setCaption("Date");
        appointmentGrid.addColumn(Appointment::getComment).setCaption("Comment");
        appointmentGrid.addColumn(Appointment::getType).setCaption("Type");

        appointmentGrid.setItems(appointmentService.findAll());

        addComponents(buttonLayout, appointmentGrid);
        setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        setExpandRatio(appointmentGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {

    }


    private void refreshGrid()
    {
        appointmentGrid.setItems( appointmentService.findAll());
    }
}
