package com.testbank.tbank.controller;

import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ClientRestController {

    @Autowired
    private ClientRepository clientRepo;


    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ClientResponse register(@Validated @RequestBody ClientRequest request) throws Exception {

        if (request.getFirstName().isEmpty()||request.getLastName().isEmpty()){
            throw new Exception("Null field");
        }

        Client client = clientRepo.save(request.getId(),request.getId(),request.getFirstName(),request.getLastName());
        ClientResponse response = new ClientResponse();
        response.setClientId(client.getId());
        return response;
    }

}
