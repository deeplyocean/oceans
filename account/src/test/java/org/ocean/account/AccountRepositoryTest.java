package org.ocean.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void addAccount(){
        Account account = Account.builder()
                .accountNo("user")
                .password("test")
                .accountName("ethan")
                .email("account@ocean.com")
                .build();
        Account newAccount = accountRepository.save(account);
        assertThat(newAccount.getId()).isNotNull();
        assertThat(newAccount.getAccountNo()).isEqualTo("user");
        assertThat(newAccount.getAccountName()).isEqualTo("ethan");

        entityManager.clear();

    }
}
