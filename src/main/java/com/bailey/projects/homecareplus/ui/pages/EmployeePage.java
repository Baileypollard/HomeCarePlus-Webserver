package com.bailey.projects.homecareplus.ui.pages;

import com.bailey.projects.homecareplus.model.Employee;
import com.bailey.projects.homecareplus.service.CustomUserDetailsService;
import com.bailey.projects.homecareplus.service.EmployeeServiceImpl;
import com.bailey.projects.homecareplus.ui.views.CreateEmployeeView;
import com.bailey.projects.homecareplus.ui.views.CreateWindowWithLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Set;

@SpringView(name = "employee")
public class EmployeePage extends VerticalLayout implements View
{
    private Grid<Employee> employeeGrid;
    private EmployeeServiceImpl employeeService;

    public EmployeePage(CustomUserDetailsService customUserDetails, EmployeeServiceImpl employeeService)
    {
        this.employeeService = employeeService;

        setHeight("100%");
        setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newEmployeeButton = new Button("New Employee", VaadinIcons.PLUS_CIRCLE);
        newEmployeeButton.addClickListener(clickEvent ->
                {
                    Collection<Window> windows = getUI().getWindows();
                    if (windows.size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    HorizontalLayout employeeLayout = new CreateEmployeeView(customUserDetails, employeeService);
                    Window window = new CreateWindowWithLayout(employeeLayout);
                    getUI().addWindow(window);
                    window.addCloseListener(closeEvent -> refreshGrid());
                }
        );
        newEmployeeButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Employee> selectedItems = employeeGrid.getSelectedItems();
            selectedItems.forEach(employee -> {
                        employeeService.removeEmployee(employee);
                        customUserDetails.deleteUserByUsername(employee.getEmployeeId());
                    }
            );
            refreshGrid();
        });

        ComboBox<String> genderComboBox = new ComboBox<String>();
        genderComboBox.setItems("Male", "Female");
        genderComboBox.setEmptySelectionAllowed(false);

        buttonLayout.addComponents(newEmployeeButton, deleteButton);

        employeeGrid = new Grid<>();
        employeeGrid.getEditor().setEnabled(true);

        employeeGrid.setWidth("100%");
        employeeGrid.setHeight("100%");

        employeeGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        employeeGrid.addColumn(Employee::getEmployeeId).setCaption("Employee Id");
        employeeGrid.addColumn(Employee::getFirstName).setCaption("First Name").setEditorComponent(new TextField(), Employee::setFirstName);
        employeeGrid.addColumn(Employee::getLastName).setCaption("Last Name").setEditorComponent(new TextField(), Employee::setLastName);
        employeeGrid.addColumn(Employee::getAddress).setCaption("Address").setEditorComponent(new TextField(), Employee::setAddress);
        employeeGrid.addColumn(Employee::getPhoneNumber).setCaption("Phone Number").setEditorComponent(new TextField(), Employee::setPhoneNumber);
        employeeGrid.addColumn(Employee::getGender).setCaption("Gender").setEditorComponent(genderComboBox, Employee::setGender);

        employeeGrid.setItems(employeeService.findAll());

        employeeGrid.getEditor().addSaveListener(editorSaveEvent -> employeeService.save(editorSaveEvent.getBean()));

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
