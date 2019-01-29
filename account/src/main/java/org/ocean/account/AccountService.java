package org.ocean.account;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public AccountDto.Response createAccount(AccountDto.Create dto) {

        if(this.accountRepository.countByEmail(dto.getEmail())>0){
            log.error("email duplicated exception. {}", dto.getEmail());
            throw new RuntimeException(dto.getEmail());
        }

        Account account = modelMapper.map(dto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account newAccount = this.accountRepository.save(account);
        log.debug("createAccount:{}", newAccount);
        return modelMapper.map(newAccount, AccountDto.Response.class);
    }

    public AccountDto.Response modifyAccount(Long id, AccountDto.Update dto){
        Account account = this.accountRepository.findById(id).get();
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        account.setAccountName(dto.getAccountName());
        account.setEmail(dto.getEmail());
        Account newAccount = this.accountRepository.save(account);
        log.debug("modifyAccount:{}", newAccount);
        return modelMapper.map(newAccount, AccountDto.Response.class);
    }

    public void removeAccount(Long id){
        this.accountRepository.deleteById(id);
    }

    public AccountDto.Response getAccountByEmail(String accountEmail){
        Account account = this.accountRepository.findByEmail(accountEmail);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
