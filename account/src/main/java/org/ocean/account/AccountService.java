package org.ocean.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public AccountDto.Response save(AccountDto.Create dto) {
        Account newAccount = new Account();
        newAccount.setUserId(dto.getUserId());
        newAccount.setPassword(passwordEncoder.encode(dto.getPassword()));
        newAccount.setUsername(dto.getUsername());
        newAccount.setEmail(dto.getEmail());
        Account account = accountRepository.save(newAccount);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(username);
        User user = new User(account.getUserId(), account.getPassword(), getAuthorities());
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
