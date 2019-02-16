package com.techprimers.security.securitydbexample.ui.Pages;

import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.techprimers.security.securitydbexample.ui.views.CreateClientView;
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
                    getUI().addWindow(new CreateWindowWithLayout(clientView));
                }
        );
        newClientButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button deleteButton = new Button("Delete", VaadinIcons.CLOSE);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent ->
        {
            Set<Client> selectedItems = clientGrid.getSelectedItems();
            selectedItems.forEach(client -> clientService.removeClientById(client.getClient_id()));
            refreshGrid();
        });

        buttonLayout.addComponents(newClientButton, deleteButton);

        clientGrid = new Grid<>();
        clientGrid.setWidth("100%");
        clientGrid.setHeight("100%");

        clientGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        clientGrid.addColumn(Client::getFirst_name).setCaption("First Name");
        clientGrid.addColumn(Client::getLast_name).setCaption("Last Name");


        clientGrid.setItems(clientService.findAll());

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
