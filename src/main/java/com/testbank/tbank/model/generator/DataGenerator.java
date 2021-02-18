package com.testbank.tbank.model.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import com.testbank.tbank.model.service.AccountRepository;
import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.service.RegisterRepository;
import com.testbank.tbank.model.entity.Register;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(AccountRepository accountRepository, RegisterRepository registerRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (accountRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Account entities...");
            ExampleDataGenerator<Account> accountRepositoryGenerator = new ExampleDataGenerator<>(Account.class,
                    LocalDateTime.of(2021, 2, 18, 0, 0, 0));
            accountRepositoryGenerator.setData(Account::setId, DataType.WORD);
            accountRepositoryGenerator.setData(Account::setId, DataType.WORD);
            accountRepositoryGenerator.setData(Account::setAccountNum, DataType.NUMBER_UP_TO_1000);
            accountRepositoryGenerator.setData(Account::setType, DataType.WORD);
            accountRepositoryGenerator.setData(Account::setBalance, DataType.NUMBER_UP_TO_1000);
            accountRepository.saveAll(accountRepositoryGenerator.create(0, seed));

            logger.info("... generating 100 Register entities...");
            ExampleDataGenerator<Register> registerRepositoryGenerator = new ExampleDataGenerator<>(Register.class,
                    LocalDateTime.of(2021, 2, 18, 0, 0, 0));
            registerRepositoryGenerator.setData(Register::setId, DataType.WORD);
            registerRepositoryGenerator.setData(Register::setPaymentId, DataType.NUMBER_UP_TO_100);
            registerRepositoryGenerator.setData(Register::setTimestamp, DataType.DATE_OF_BIRTH);
            registerRepositoryGenerator.setData(Register::setSorceId, DataType.NUMBER_UP_TO_100);
            registerRepositoryGenerator.setData(Register::setDestId, DataType.NUMBER_UP_TO_100);
            registerRepositoryGenerator.setData(Register::setAmount, DataType.NUMBER_UP_TO_1000);
            registerRepositoryGenerator.setData(Register::setClientPayer, DataType.NUMBER_UP_TO_100);
            registerRepositoryGenerator.setData(Register::setClientRecipient, DataType.NUMBER_UP_TO_100);
            registerRepository.saveAll(registerRepositoryGenerator.create(0, seed));

            logger.info("Generated demo data");
        };
    }

}