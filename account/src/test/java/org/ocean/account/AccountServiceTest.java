package org.ocean.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @TestConfiguration
    static class testContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Before
    public void setUp() {
        Account account = Account.builder()
                .accountNo("user")
                .password("test")
                .accountName("ethan")
                .email("account@ocean.com")
                .build();

        Mockito.when(accountRepository.findByEmail(account.getEmail()))
                .thenReturn(account);
    }

    @Test
    public void getAccountByEmail(){
        Account account = Account.builder()
                .accountNo("user")
                .password("test")
                .accountName("ethan")
                .email("account@ocean.com")
                .build();
        Account findAccount = accountService.getAccountByEmail(account.getEmail());
        assertThat(findAccount).isEqualTo(account);
    }
}
