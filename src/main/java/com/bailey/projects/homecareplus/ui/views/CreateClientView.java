package com.bailey.projects.homecareplus.ui.views;

import com.bailey.projects.homecareplus.model.Client;
import com.bailey.projects.homecareplus.service.ClientServiceImpl;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;

public class CreateClientView extends HorizontalLayout
{
    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private TextField address;
    private TextField healthCard;
    private TextField emergencyContactName;
    private TextField emergencyNumber;


    private ComboBox<String> gender;
    private TextArea additionalInformation;

    public CreateClientView(ClientServiceImpl clientService)
    {
        setCaption("New Client");
        setWidth("100%");
        setHeight("100%");
        setSpacing(true);

        FormLayout leftLayout = new FormLayout();
        firstName = new TextField("First Name: ");
        lastName = new TextField("Last Name: ");
        phoneNumber = new TextField("Phone Number: ");
        address = new TextField("Address: ");
        gender = new ComboBox<String>("Gender");
        gender.setItems("Male", "Female");
        gender.setEmptySelectionAllowed(false);

        additionalInformation = new TextArea("Additional Information: ");

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

        leftLayout.addComponents(firstName, lastName, address, phoneNumber, gender,
                additionalInformation, createButton);


        FormLayout rightLayout = new FormLayout();

        healthCard = new TextField("Health Card #: ");
        emergencyContactName = new TextField("Emergency Contact Name: ");
        emergencyNumber = new TextField("Emergency Phone #: ");


        rightLayout.addComponents(healthCard, emergencyContactName, emergencyNumber);

        addComponents(leftLayout, rightLayout); //Main horizontal Layout

        setComponentAlignment(leftLayout, Alignment.MIDDLE_LEFT);
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
            String additionalInfo = this.additionalInformation.getValue();
            String healthCardNumber = this.healthCard.getValue();
            String emergencyContactName = this.emergencyContactName.getValue();
            String emergencyContactPhone = this.emergencyNumber.getValue();

            return new Client(UUID.randomUUID().toString(), firstName,
                    lastName, address, gender, phoneNumber, additionalInfo,
                    healthCardNumber, emergencyContactName,
                    emergencyContactPhone);
        }
        catch (NoSuchElementException e)
        {
            Notification.show("Please ensure there are no empty fields", Notification.Type.ERROR_MESSAGE);
            return null;
        }
    }
}
