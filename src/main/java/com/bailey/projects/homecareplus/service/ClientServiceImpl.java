package com.bailey.projects.homecareplus.service;

import com.bailey.projects.homecareplus.interfaces.ClientService;
import com.bailey.projects.homecareplus.repository.ClientRepository;
import com.bailey.projects.homecareplus.utils.DocumentCreator;
import com.bailey.projects.homecareplus.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client)
    {
        return clientRepository.createClient(client.getClientId(), DocumentCreator.createClientDocument(client));
    }

    @Override
    public void removeClient(Client client)
    {
        clientRepository.removeClient(client.getClientId());
    }

    @Override
    public void save(Client client)
    {
        clientRepository.save(client.getClientId(), client.getFirstName(), client.getLastName(), client.getAddress(),
                client.getGender(), client.getPhoneNumber(), client.getAdditionalInformation());
    }
}
