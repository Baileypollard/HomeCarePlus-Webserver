package com.techprimers.security.securitydbexample.ui.pages;

import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.techprimers.security.securitydbexample.ui.views.CreateClientView;
import com.techprimers.security.securitydbexample.ui.views.CreateWindowWithLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.Set;


@SpringView(name = "client")
public class ClientPage extends VerticalLayout implements View
{
    private Grid<Client> clientGrid;
    private ClientServiceImpl clientService;

    public ClientPage(ClientServiceImpl clientService)
    {
        this.clientService = clientService;
        setHeight("100%");
        setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button newClientButton = new Button("New Client", VaadinIcons.PLUS_CIRCLE);
        newClientButton.addClickListener(clickEvent ->
                {
                    Collection<Window> windows = getUI().getWindows();
                    if (windows.size() > 0)
                    {
                        windows.forEach(Window::close);
                    }
                    CreateClientView clientView = new CreateClientView(clientService);
                    Window window = new CreateWindowWithLayout(clientView);
                    getUI().addWindow(window);
                    window.addCloseListener(closeEvent -> refreshGrid());
                }
        );
        newClientButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Client> selectedItems = clientGrid.getSelectedItems();
            selectedItems.forEach(clientService::removeClient);
            refreshGrid();
        });
        ComboBox<String> genderComboBox = new ComboBox<String>();
        genderComboBox.setItems("Male", "Female");
        genderComboBox.setEmptySelectionAllowed(false);

        buttonLayout.addComponents(newClientButton, deleteButton);

        clientGrid = new Grid<>();
        clientGrid.getEditor().setEnabled(true);
        clientGrid.setSizeFull();

        clientGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        clientGrid.addColumn(Client::getFirstName).setCaption("First Name").setEditorComponent(new TextField(), Client::setFirstName);
        clientGrid.addColumn(Client::getLastName).setCaption("Last Name").setEditorComponent(new TextField(), Client::setLastName);
        clientGrid.addColumn(Client::getAddress).setCaption("Address").setEditorComponent(new TextField(), Client::setAddress);
        clientGrid.addColumn(Client::getPhoneNumber).setCaption("Phone Number").setEditorComponent(new TextField(), Client::setPhoneNumber);
        clientGrid.addColumn(Client::getGender).setCaption("Gender").setEditorComponent(genderComboBox, Client::setGender);
        clientGrid.addColumn(Client::getAdditionalInformation).setCaption("Additional Information").setEditorComponent(new TextField(), Client::setAdditionalInformation);

        clientGrid.setItems(clientService.findAll());
        clientGrid.getEditor().addSaveListener(editorSaveEvent -> { clientService.save(editorSaveEvent.getBean()); });

        addComponents(buttonLayout, clientGrid);
        setComponentAlignment(buttonLayout, Alignment.TOP_RIGHT);
        setExpandRatio(clientGrid, 1.0f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {

    }

    private void refreshGrid()
    {
        clientGrid.setItems(clientService.findAll());
    }
}
