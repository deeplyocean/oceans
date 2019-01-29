package org.ocean.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;


    @Test
    @Rollback
    public void createAccount(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getAccountNo()).isEqualTo(dto.getAccountNo());
        assertThat(result.getAccountName()).isEqualTo(dto.getAccountName());
    }

    @Test
    @Rollback
    public void getAccountByEmail(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);

        AccountDto.Response findAccount = accountService.getAccountByEmail(result.getEmail());
        assertThat(findAccount).isEqualTo(result);
    }

    private AccountDto.Create createAccountDto(){
        AccountDto.Create dto = AccountDto.Create.builder()
                .accountNo("user")
                .password("test")
                .accountName("ethan")
                .email(String.format("account%s@ocean.com", System.currentTimeMillis()))
                .build();
        return dto;
    }
}
