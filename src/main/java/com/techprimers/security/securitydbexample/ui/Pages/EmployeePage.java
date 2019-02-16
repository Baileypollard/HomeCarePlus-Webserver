package com.techprimers.security.securitydbexample.ui.Pages;

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

    public EmployeePage(EmployeeServiceImpl employeeService)
    {
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
                    getUI().addWindow(new CreateWindowWithLayout(employeeLayout));
                }
        );
        newClientButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Employee> selectedItems = employeeGrid.getSelectedItems();
            selectedItems.forEach(employee -> employeeService.removeEmployeeById(employee.getEmployee_id()));
            employeeGrid.setItems(employeeService.findAll());
        });

        buttonLayout.addComponents(newClientButton, deleteButton);

        employeeGrid = new Grid<>();
        employeeGrid.setWidth("100%");
        employeeGrid.setHeight("100%");

        employeeGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        employeeGrid.addColumn(Employee::getFirst_name).setCaption("First Name");
        employeeGrid.addColumn(Employee::getLast_name).setCaption("Last Name");

        employeeGrid.setItems(employeeService.findAll());

        addComponents(buttonLayout, employeeGrid);
        setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        setExpandRatio(employeeGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {

    }
}
