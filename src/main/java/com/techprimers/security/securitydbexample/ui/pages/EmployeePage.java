package com.techprimers.security.securitydbexample.ui.pages;

import com.techprimers.security.securitydbexample.model.Employee;
import com.techprimers.security.securitydbexample.service.EmployeeServiceImpl;
import com.techprimers.security.securitydbexample.ui.views.CreateEmployeeView;
import com.techprimers.security.securitydbexample.ui.views.CreateWindowWithLayout;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Set;

@PreserveOnRefresh
public class EmployeePage extends VerticalLayout implements View
{
    private Grid<Employee> employeeGrid;
    private EmployeeServiceImpl employeeService;

    public EmployeePage(EmployeeServiceImpl employeeService)
    {
        this.employeeService = employeeService;

        setHeight("100%");
        setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newClientButton = new Button("New Employee", VaadinIcons.PLUS_CIRCLE);
        newClientButton.addClickListener(clickEvent ->
                {
                    Collection<Window> windows = getUI().getWindows();
                    if (windows.size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    VerticalLayout employeeLayout = new CreateEmployeeView(employeeService);
                    Window window = new CreateWindowWithLayout(employeeLayout);
                    getUI().addWindow(window);
                    window.addCloseListener(closeEvent -> refreshGrid());
                }
        );
        newClientButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Employee> selectedItems = employeeGrid.getSelectedItems();
            selectedItems.forEach(employeeService::removeEmployee);
            refreshGrid();
        });

        buttonLayout.addComponents(newClientButton, deleteButton);

        employeeGrid = new Grid<>();
        employeeGrid.setWidth("100%");
        employeeGrid.setHeight("100%");

        employeeGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        employeeGrid.addColumn(Employee::getEmployeeId).setCaption("Employee Id");
        employeeGrid.addColumn(Employee::getFirstName).setCaption("First Name");
        employeeGrid.addColumn(Employee::getLastName).setCaption("Last Name");
        employeeGrid.addColumn(Employee::getAddress).setCaption("Address");
        employeeGrid.addColumn(Employee::getPhoneNumber).setCaption("Phone Number");
        employeeGrid.addColumn(Employee::getGender).setCaption("Gender");

        employeeGrid.setItems(employeeService.findAll());

        addComponents(buttonLayout, employeeGrid);
        setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        setExpandRatio(employeeGrid, 1.0f);
    }

    private void refreshGrid()
    {
        employeeGrid.setItems(employeeService.findAll());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {

    }
}
