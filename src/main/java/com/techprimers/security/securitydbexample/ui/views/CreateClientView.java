package com.techprimers.security.securitydbexample.ui.views;

import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;

public class CreateClientView extends VerticalLayout
{
    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private TextField address;
    private ComboBox<String> gender;

    public CreateClientView(ClientServiceImpl clientService)
    {
        setCaption("New Client");
        setWidth("100%");
        setHeight("100%");
        setSpacing(true);

        FormLayout layout = new FormLayout();
        firstName = new TextField("First Name: ");
        lastName = new TextField("Last Name: ");
        phoneNumber = new TextField("Phone Number: ");
        address = new TextField("Address: ");
        gender = new ComboBox<String>("Gender");
        gender.setItems("Male", "Female");

        Button createButton = new Button("Create Client");
        createButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        createButton.addClickListener(clickEvent -> {
            Client client = createNewClient();
            if (client != null)
            {
                clientService.createClient(client);
                Collection<Window> windows = getUI().getWindows();
                windows.forEach(Window::close);
            }
        });

        layout.addComponents(firstName, lastName, address, phoneNumber, gender, createButton);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    private Client createNewClient()
    {
        try
        {
            String firstName = this.firstName.getValue();
            String lastName = this.lastName.getValue();
            String address = this.address.getValue();
            String phoneNumber = this.phoneNumber.getValue();
            String gender = this.gender.getValue();

            return new Client(UUID.randomUUID().toString(), firstName, lastName, address, gender, phoneNumber);
        }
        catch (NoSuchElementException e)
        {
            Notification.show("Please ensure there are no empty fields", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
