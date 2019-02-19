package com.techprimers.security.securitydbexample.ui.pages;

import com.techprimers.security.securitydbexample.interfaces.AppointmentService;
import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.ui.views.CreateAppointmentView;
import com.techprimers.security.securitydbexample.ui.views.CreateWindowWithLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Set;

public class AppointmentPage extends VerticalLayout implements View
{
    private AppointmentService appointmentService;
    private Grid<Appointment> appointmentGrid;

    public AppointmentPage(EmployeeService employeeService, ClientService clientService, AppointmentService appointmentService)
    {
        this.appointmentService = appointmentService;

        setSizeFull();

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
                    VerticalLayout appointmentLayout = new CreateAppointmentView(employeeService, clientService, appointmentService);
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
            selectedItems.forEach(appointment -> appointmentService.removeAppointmentByAppointmentId(appointment.getAppointment_id()));
            refreshGrid();
        });

        buttonLayout.addComponents(newAppointmentButton, deleteButton);

        appointmentGrid = new Grid<>();
        appointmentGrid.setSizeFull();

        appointmentGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        appointmentGrid.addColumn(Appointment::getEmployee_id).setCaption("Employee").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getFirst_name).setCaption("First Name").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getLast_name).setCaption("Last Name").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getAddress).setCaption("Address").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getStart_time).setCaption("Start Time").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getEnd_time).setCaption("End Time").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getDate).setCaption("Date").setWidthUndefined();
        appointmentGrid.addColumn(Appointment::getComment).setCaption("Comment").setWidthUndefined();

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
