package org.ocean.account;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.ocean.type.Email;
import org.ocean.type.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private AccountService accountService;

    @Test
    @Rollback
    public void getAccountListTest(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);

        List<AccountDto.Response> accountList = accountService.getAccountList();
        assertThat(accountList).isNotEmpty();
        assertThat(accountList).contains(result);
    }

    @Test
    @Rollback
    public void getAccountTest(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);

        AccountDto.Response account = accountService.getAccount(result.getId());
        assertThat(account).isNotNull();
        assertThat(account).isEqualTo(result);
    }

    @Test
    @Rollback
    public void getAccountNotFoundTest(){
        Long id = 0L;
        expectedException.expect(AccountNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(String.valueOf(id)));
        accountService.getAccount(id);
    }

    @Test
    @Rollback
    public void createAccount(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getEmail()).isEqualTo(dto.getEmail());
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

    @Test
    @Rollback
    public void modifyAccountTest(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);

        AccountDto.Update updateDto = AccountDto.Update.builder()
                .email(result.getEmail())
                .password(Password.builder().value("test1234").build())
                .accountName("updatedName")
                .roles(result.getRoles())
                .build();
        AccountDto.Response updateAccount = accountService.modifyAccount(result.getId(), updateDto);
        assertThat(updateAccount.getId()).isEqualTo(result.getId());
        assertThat(updateAccount.getEmail()).isEqualTo(result.getEmail());
        assertThat(updateAccount.getRoles()).isEqualTo(result.getRoles());
        assertThat(updateAccount.getAccountName()).isEqualTo(updateDto.getAccountName());
    }

    @Test
    @Rollback
    public void removeAccountTest(){
        AccountDto.Create dto = createAccountDto();
        AccountDto.Response result = accountService.createAccount(dto);

        AccountDto.Response account = accountService.getAccount(result.getId());
        assertThat(account).isNotNull();
        assertThat(account).isEqualTo(result);

        accountService.removeAccount(result.getId());

        expectedException.expect(AccountNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(String.valueOf(result.getId())));
        accountService.getAccount(result.getId());

    }

    private AccountDto.Create createAccountDto(){
        AccountDto.Create dto = AccountDto.Create.builder()
                .email(Email.builder().value(String.format("account%s@ocean.com", System.currentTimeMillis())).build())
                .password(Password.builder().value("test1234").build())
                .accountName("ethan")
                .roles(Arrays.asList(AccountRoles.ADMIN))
                .build();
        return dto;
    }
}
