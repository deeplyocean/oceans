package org.ocean.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public List<Account> getAccountList(){
        return this.accountRepository.findAll();
    }

    public Account getAccount(Long id){
        return this.accountRepository.findById(id).get();
    }

    public Account createAccount(Account account){
        return this.accountRepository.save(account);
    }

    public Account modifyAccount(Long id, Account account){
        Account orgAccount = getAccount(id);
        orgAccount.setPassword(account.getPassword());
        orgAccount.setAccountName(account.getAccountName());
        orgAccount.setEmail(account.getEmail());
        return this.accountRepository.save(orgAccount);
    }

    public void removeAccount(Long id){
        this.accountRepository.deleteById(id);
    }

    public Account getAccountByEmail(String accountEmail){
        return this.accountRepository.findByEmail(accountEmail);
    }
}
