package com.testbank.tbank.controller;

import com.testbank.tbank.controller.request.AccountRequest;
import com.testbank.tbank.controller.request.ClientRequest;
import com.testbank.tbank.controller.request.RegisterRequest;
import com.testbank.tbank.controller.response.AccountResponse;
import com.testbank.tbank.controller.response.ClientResponse;
import com.testbank.tbank.controller.response.RegisterResponse;
import com.testbank.tbank.exceptions.*;
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
    List<AccountResponse> clientAccounts(@Validated @RequestBody AccountRequest request) {
        List<Account> accounts = new ArrayList<>();
        if (request.getClientId() == null) {
            throw new NoContentException("Invalid request body");
        }

        if (clientService.findById(request.getClientId()) != null)
            accounts = accountService.getAccountsByClientId(request.getClientId());

        List<AccountResponse> response = mapAccount(accounts);
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

        addAmount(request.getSorceId(), -request.getAmount());
        addAmount(request.getDestId(), request.getAmount());
        Register register = registerService.createPayment(mapRegister(String.valueOf(date), request.getSorceId(), request.getDestId(), request.getAmount()));
        RegisterResponse response = new RegisterResponse();
        response.setPaymentId(register.getId());
        return response;
    }

    //TODO extract to mapper
    private List<AccountResponse> mapAccount(List<Account> accounts) {
        List<AccountResponse> responseList = new LinkedList<>();
        for (Account account : accounts) {
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

    private Register mapRegister(String s, String sorceId, String destId, Integer amount){
        Register register = new Register();
        register.setTimestamp(s);
        register.setSorceId(sorceId);
        register.setDestId(destId);
        register.setAmount(amount);
        return register;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(String id, Integer amount) throws BankTransactionException {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        Integer newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

}
