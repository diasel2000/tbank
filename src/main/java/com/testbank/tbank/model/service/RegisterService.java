package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.Date;

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

    public Register createPayment(Register register) {
        return repository.save(register);
    }

    public Register findPaymentBySourceIdAndDestId(String sourceId, String destId) {
        return repository.findRegisterBySorceIdAndDestId(sourceId,destId);
    }
}
