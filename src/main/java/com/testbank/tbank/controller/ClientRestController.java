package com.testbank.tbank.controller;

import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.service.AccountRepository;
import com.testbank.tbank.model.service.AccountService;
import com.testbank.tbank.model.service.ClientRepository;
import com.testbank.tbank.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(path = "/register", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ClientResponse register(@Validated @RequestBody ClientRequest request) throws Exception {

        if (request.getFirstName() == null || request.getLastName() == null) {
            throw new Exception("Null field");
        }
        Client client = clientService.saveClient(getClient(request.getId(), request.getFirstName(), request.getLastName(), request.getAccaunts()));
        ClientResponse response = new ClientResponse();
        response.setClientId(client.getId());
        return response;
    }

    public Client getClient(String id, String firstName, String lastName, Set<Account> accaunts) {
        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        for (Account account : accaunts) {
            client.addAccount(account);
        }
        return client;
    }


}
