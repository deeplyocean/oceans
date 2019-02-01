package org.ocean.account;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message){
        super(String.format("Not Found Account[%s].", message));
    }

    public AccountNotFoundException(Long id){
        super(String.format("Not Found Account[%d].", id));
    }
}
