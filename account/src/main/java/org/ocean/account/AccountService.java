package org.ocean.account;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.ocean.type.Email;
import org.ocean.type.Password;
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
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        return modelMapper.map(account, AccountDto.Response.class);
    }

    public AccountDto.Response createAccount(AccountDto.Create dto) {

        if(this.accountRepository.countByEmail(dto.getEmail())>0){
            log.error("email duplicated exception. {}", dto.getEmail());
            throw new RuntimeException(dto.getEmail().getValue());
        }

        Account account = modelMapper.map(dto, Account.class);
        account.updatePassword(Password.builder().value(passwordEncoder.encode(dto.getPassword().getValue())).build());
        Account newAccount = this.accountRepository.save(account);
        log.debug("createAccount:{}", newAccount);
        return modelMapper.map(newAccount, AccountDto.Response.class);
    }

    public AccountDto.Response modifyAccount(Long id, AccountDto.Update dto){
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        account.updateAccount(modelMapper.map(dto, Account.class));
        account.updatePassword(Password.builder().value(passwordEncoder.encode(dto.getPassword().getValue())).build());
        Account newAccount = this.accountRepository.save(account);
        log.debug("modifyAccount:{}", newAccount);
        return modelMapper.map(newAccount, AccountDto.Response.class);
    }

    public void removeAccount(Long id){
        this.accountRepository.deleteById(id);
    }

    public AccountDto.Response getAccountByEmail(Email email){
        Account account = this.accountRepository.findByEmail(email);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByEmail(Email.builder().value(username).build());
        return new User(account.getEmail().getValue(), account.getPassword().getValue(), authorities(account.getRoles()));
    }

    private List<GrantedAuthority> authorities(List<AccountRoles> list){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(AccountRoles roles : list){
            authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", roles.name())));
        }
        return authorities;
    }
}
