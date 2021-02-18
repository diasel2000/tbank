package com.testbank.tbank.controller;

import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.service.AccountRepository;
import com.testbank.tbank.model.service.AccountService;
import com.testbank.tbank.model.service.ClientRepository;
import com.testbank.tbank.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/register", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ClientResponse register(@Validated @RequestBody ClientRequest request) throws Exception {

        if (request.getFirstName() == null || request.getLastName() == null) {
            throw new Exception("Null field");
        }
        //accountService.save(request.getAccaunts());
        Client client = clientService.saveClient(getClient(request.getId(), request.getFirstName(), request.getLastName()));
        ClientResponse response = new ClientResponse();
        response.setClientId(client.getId());
        return response;
    }

    public Client getClient(String id, String firstName, String lastName) {
        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        return client;
    }


}
