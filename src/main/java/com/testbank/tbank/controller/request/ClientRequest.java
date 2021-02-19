package com.testbank.tbank.controller.request;

import com.testbank.tbank.model.entity.Account;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class ClientRequest {

    private String id;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String lastName;

    @NotEmpty
    private Set<Account> accaunts;

}
