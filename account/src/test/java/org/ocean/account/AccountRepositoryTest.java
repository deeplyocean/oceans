package org.ocean.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocean.type.Email;
import org.ocean.type.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
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
    @Rollback
    public void addAccount(){
        Account account = Account.builder()
                .email(Email.builder().value(String.format("account%s@ocean.com", System.currentTimeMillis())).build())
                .password(Password.builder().value("test1234").build())
                .accountName("ethan")
                .build();
        Account newAccount = accountRepository.save(account);
        assertThat(newAccount.getId()).isNotNull();
        assertThat(newAccount.getEmail().getValue()).isEqualTo(account.getEmail().getValue());
        assertThat(newAccount.getAccountName()).isEqualTo(account.getAccountName());

        entityManager.clear();
    }
}
