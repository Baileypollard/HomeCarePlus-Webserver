package com.bailey.projects.homecareplus.interfaces;

import com.bailey.projects.homecareplus.model.Client;
import java.util.List;

public interface ClientService
{
    List<Client> findAll();

    Client createClient(Client client);

    void removeClient(Client client);

    void save(Client client);

}
