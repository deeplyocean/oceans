package org.ocean.account;

import org.ocean.type.Email;

public class AccountDuplicateException extends RuntimeException {

    private final Email email;

    public AccountDuplicateException(Email email){
        super(String.format("Duplicated Account[%s].", email.getValue()));
        this.email = email;
    }

    public Email getEmail(){
        return this.email;
    }
}
