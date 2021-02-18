package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "insert into client values (:clientId, :accId, :firstName, :lastName)", nativeQuery = true)
    Client save(@Param("clientId") String id, @Param("accId") String accId, @Param("firstName")String firstName, @Param("lastName")String lastName);

}