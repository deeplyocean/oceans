package org.ocean.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create dto, BindingResult result){

        if(result.hasErrors()){
            FormErrorInfo errorResponse = new FormErrorInfo();
            errorResponse.setCode("bad.request");
            errorResponse.setMessage("잘못된 요청입니다.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        AccountDto.Response account = accountService.save(dto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

}
