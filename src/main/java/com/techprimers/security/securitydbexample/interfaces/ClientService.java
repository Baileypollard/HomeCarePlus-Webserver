package com.techprimers.security.securitydbexample.interfaces;

import com.techprimers.security.securitydbexample.model.Client;
import java.util.List;

public interface ClientService
{
    List<Client> findAll();

    Client createClient(Client client);

    void removeClient(Client client);

}
