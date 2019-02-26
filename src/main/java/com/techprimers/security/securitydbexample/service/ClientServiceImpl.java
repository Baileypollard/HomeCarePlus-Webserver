package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.interfaces.ClientService;
import com.techprimers.security.securitydbexample.utils.DocumentCreator;
import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.repository.ClientRepository;
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
