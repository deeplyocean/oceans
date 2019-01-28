package org.ocean.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Account> getAccountList(){
        return this.accountRepository.findAll();
    }

    public Account getAccount(Long id){
        return this.accountRepository.findById(id).get();
    }

    public Account createAccount(AccountDto.Create dto){
        Account account = modelMapper.map(dto, Account.class);
        return this.accountRepository.save(account);;
    }

    public Account modifyAccount(Long id, AccountDto.Update dto){
        Account account = getAccount(id);
        account.setPassword(dto.getPassword());
        account.setAccountName(dto.getAccountName());
        account.setEmail(dto.getEmail());
        return this.accountRepository.save(account);
    }
}
