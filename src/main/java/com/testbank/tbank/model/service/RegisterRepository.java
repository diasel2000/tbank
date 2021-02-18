package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Register;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Integer> {

}