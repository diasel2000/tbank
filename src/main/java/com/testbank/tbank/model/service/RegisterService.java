package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class RegisterService extends CrudService<Register, Integer> {

    private RegisterRepository repository;

    public RegisterService(@Autowired RegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RegisterRepository getRepository() {
        return repository;
    }

}
