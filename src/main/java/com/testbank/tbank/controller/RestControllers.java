package com.testbank.tbank.controller;

import com.testbank.tbank.controller.request.AccountRequest;
import com.testbank.tbank.controller.request.ClientRequest;
import com.testbank.tbank.controller.request.JurnalRequest;
import com.testbank.tbank.controller.request.RegisterRequest;
import com.testbank.tbank.controller.response.AccountResponse;
import com.testbank.tbank.controller.response.ClientResponse;
import com.testbank.tbank.controller.response.JurnalResponse;
import com.testbank.tbank.controller.response.RegisterResponse;
import com.testbank.tbank.exceptions.*;
import com.testbank.tbank.mapper.Mapper;
import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.entity.Register;
import com.testbank.tbank.model.service.AccountService;
import com.testbank.tbank.model.service.ClientService;
import com.testbank.tbank.model.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class RestControllers {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(path = "/register", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ClientResponse register(@Validated @RequestBody ClientRequest request) throws Exception {

        if (request == null)
            throw new NoContentException("Invalid request body");

        if (request.getFirstName() == null || request.getLastName() == null)
            throw new ClientNotFoundException("Fields must not be empty");

        if (request.getAccaunts().isEmpty())
            throw new EntryNotFoundException("The client must have at least one account");

        Client client = clientService.saveClient(mapper.mapClient(request.getId(), request.getFirstName(), request.getLastName(), request.getAccaunts()));
        ClientResponse response = new ClientResponse();
        response.setClientId(client.getId());
        return response;
    }

    @RequestMapping(path = "/clientAcc", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AccountResponse> clientAccounts(@Validated @RequestBody AccountRequest request) {
        List<Account> accounts = new ArrayList<>();
        if (request.getClientId() == null) {
            throw new NoContentException("Invalid request body");
        }

        if (clientService.findById(request.getClientId()) != null)
            accounts = accountService.getAccountsByClientId(request.getClientId());

        List<AccountResponse> response = mapper.mapAccount(accounts);
        return response;
    }

    @RequestMapping(path = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RegisterResponse payments(@Validated @RequestBody RegisterRequest request) throws Exception {
        Date date = new Date();

        if (request.getDestId().isEmpty() || request.getSorceId().isEmpty())
            throw new InvalidInputException("Account id's can't be null");

        if (accountService.findById(request.getSorceId()) == null || accountService.findById(request.getDestId()) == null)
            throw new EntryInconsistencyException("Account not found");

        mapper.addAmount(request.getSorceId(), -request.getAmount());
        mapper.addAmount(request.getDestId(), request.getAmount());
        Register register = registerService.createPayment(mapper.mapRegister(String.valueOf(date), request.getSorceId(), request.getDestId(), request.getAmount()));
        RegisterResponse response = new RegisterResponse();
        response.setPaymentId(register.getId());
        return response;
    }

    @RequestMapping(path = "/jurnal", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JurnalResponse jurnal(@Validated @RequestBody JurnalRequest request) throws Exception {
        //TODO implement validation

        JurnalResponse response = new JurnalResponse();
        //response.setPaymentId(register.getId());
        return response;
    }
}
