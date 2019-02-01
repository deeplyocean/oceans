package org.ocean.account;

import org.ocean.type.Email;

public class AccountNotFoundException extends RuntimeException {

    private Long id;
    private Email email;

    public AccountNotFoundException(Long id){
        super(String.format("Not Found Account ID[%d]", id));
        this.id = id;
    }

    public AccountNotFoundException(Email email){
        super(String.format("Not Found Account Email[%s]", email.getValue()));
        this.email = email;
    }

    public Long getId(){
        return this.id;
    }

    public Email getEmail(){
        return this.email;
    }
}
