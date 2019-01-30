package org.ocean.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity getAccountList(){
        List<AccountDto.Response> accountList = accountService.getAccountList();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create dto, Errors errors){
        log.debug("createAccount:dto::{}", dto);
        if(errors.hasErrors()){
            log.error("{}", errors.getAllErrors());
            return ResponseEntity.badRequest().build();
        }

        AccountDto.Response account = accountService.createAccount(dto);
        log.debug("{}", account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
