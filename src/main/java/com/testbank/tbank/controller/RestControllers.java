package com.testbank.tbank.controller;

import com.testbank.tbank.controller.request.AccountRequest;
import com.testbank.tbank.controller.request.ClientRequest;
import com.testbank.tbank.controller.response.AccountResponse;
import com.testbank.tbank.controller.response.ClientResponse;
import com.testbank.tbank.exceptions.ClientNotFoundException;
import com.testbank.tbank.exceptions.EntryNotFoundException;
import com.testbank.tbank.exceptions.NoContentException;
import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.service.AccountService;
import com.testbank.tbank.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(path = "/register", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ClientResponse register(@Validated @RequestBody ClientRequest request) throws Exception {

        if (request == null)
            throw new NoContentException("Invalid request body");

        if (request.getFirstName() == null || request.getLastName() == null)
            throw new ClientNotFoundException("Fields must not be empty");

        if (request.getAccaunts().isEmpty())
            throw new EntryNotFoundException("The client must have at least one account");

        Client client = clientService.saveClient(mapClient(request.getId(), request.getFirstName(), request.getLastName(), request.getAccaunts()));
        ClientResponse response = new ClientResponse();
        response.setClientId(client.getId());
        return response;
    }

    @RequestMapping(path = "/clientAcc", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AccountResponse> clientAccounts(@Validated @RequestBody AccountRequest request) throws Exception {
        List<Account> accounts = new ArrayList<>();
        if (request.getClientId() == null) {
            throw new NoContentException("Invalid request body");
        }

        if (clientService.findById(request.getClientId()) != null)
             accounts = accountService.getAccountsByClientId(request.getClientId());

        List<AccountResponse> response = mapAccount(accounts);
        return response;
    }

    //TODO extract to mapper
    private List<AccountResponse> mapAccount(List<Account> accounts) {
        List<AccountResponse> responseList = new LinkedList<>();
        for (Account account:accounts){
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(account.getId());
            accountResponse.setAccountNum(account.getAccountNum());
            accountResponse.setType(account.getType());
            accountResponse.setBalance(account.getBalance());
            responseList.add(accountResponse);
        }
        return responseList;
    }

    private Client mapClient(String id, String firstName, String lastName, Set<Account> accaunts) {
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
