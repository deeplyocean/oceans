package org.ocean.account;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AccountDto.Response> getAccountList(){
        List<Account> accountList = this.accountRepository.findAll();
        Type listType = new TypeToken<List<AccountDto.Response>>() {
        }.getType();
        return modelMapper.map(accountList, listType);
    }

    public AccountDto.Response getAccount(Long id){
        Account account = this.accountRepository.findById(id).get();
        return modelMapper.map(account, AccountDto.Response.class);
    }

    public AccountDto.Response createAccount(AccountDto.Create dto){
        Account account = this.accountRepository.save(modelMapper.map(dto, Account.class));
        log.debug("{}", account);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    public AccountDto.Response modifyAccount(Long id, AccountDto.Update dto){
        Account account = this.accountRepository.findById(id).get();
        account.setPassword(dto.getPassword());
        account.setAccountName(dto.getAccountName());
        account.setEmail(dto.getEmail());
        Account newAcount = this.accountRepository.save(account);
        return modelMapper.map(newAcount, AccountDto.Response.class);
    }

    public void removeAccount(Long id){
        this.accountRepository.deleteById(id);
    }

    public AccountDto.Response getAccountByEmail(String accountEmail){
        Account account = this.accountRepository.findByEmail(accountEmail);
        return modelMapper.map(account, AccountDto.Response.class);
    }
}
