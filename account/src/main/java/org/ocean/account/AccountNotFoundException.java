package org.ocean.account;

public class AccountNotFoundException extends RuntimeException {

    private final Long id;

    public AccountNotFoundException(Long id){
        super(String.format("Not Found Account[%d].", id));
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }
}
