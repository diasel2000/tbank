package com.testbank.tbank.model.service;

import com.testbank.tbank.controller.ClientRequest;
import com.testbank.tbank.model.entity.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.UUID;

@Service
public class ClientService extends CrudService<Client, Integer> {

    private ClientRepository repository;

    public ClientService(@Autowired ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ClientRepository getRepository() {
        return repository;
    }

    public Client saveClient(Client client) {
        client.setId(idGenrate());
        client.setAccId(idGenrate());
        return repository.save(client);
    }

    private String idGenrate() {
        return UUID.randomUUID().toString();
    }

}
