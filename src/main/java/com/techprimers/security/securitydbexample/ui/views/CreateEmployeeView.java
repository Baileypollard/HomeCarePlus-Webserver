package com.techprimers.security.securitydbexample.ui.views;

import com.techprimers.security.securitydbexample.model.Employee;
import com.techprimers.security.securitydbexample.service.EmployeeServiceImpl;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.data.couchbase.core.CouchbaseQueryExecutionException;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CreateEmployeeView extends VerticalLayout
{
    private TextField userName;
    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private TextField address;
    private ComboBox<String> gender;

    public CreateEmployeeView(EmployeeServiceImpl employeeService)
    {
        setCaption("New Employee");
        setWidth("100%");
        setHeight("100%");
        setSpacing(true);

        FormLayout layout = new FormLayout();
        userName = new TextField("Username: ");
        firstName = new TextField("First Name: ");
        lastName = new TextField("Last Name: ");
        phoneNumber = new TextField("Phone Number: ");
        address = new TextField("Address: ");
        gender = new ComboBox<String>("Gender");
        gender.setItems("Male", "Female");

        Button createButton = new Button("Create Employee");
        createButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        createButton.addClickListener(clickEvent -> {
            Employee employee = createNewEmployee();
            if (employee != null)
            {
                try
                {
                    employeeService.createNewEmployee(employee);
                    Collection<Window> windows = getUI().getWindows();
                    windows.forEach(Window::close);
                }
                catch (CouchbaseQueryExecutionException e)
                {
                    Notification.show("You can not create two employees with the same id, Please try again", Notification.Type.ERROR_MESSAGE);
                }

            }
        });

        layout.addComponents(userName, firstName, lastName, address, phoneNumber, gender, createButton);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    private Employee createNewEmployee()
    {
        try
        {
            String userName = this.userName.getValue();
            String firstName = this.firstName.getValue();
            String lastName = this.lastName.getValue();
            String address = this.address.getValue();
            String phoneNumber = this.phoneNumber.getValue();
            String gender = this.gender.getValue();

            return new Employee(userName, firstName, lastName, gender, phoneNumber, address);
        }
        catch (NoSuchElementException e)
        {
            Notification.show("Please ensure there are no empty fields", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
