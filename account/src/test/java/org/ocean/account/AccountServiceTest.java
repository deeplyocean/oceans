package org.ocean.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void createTest(){
        AccountDto.Create newAccount = new AccountDto.Create();
        newAccount.setUserId("deeplyocean");
        newAccount.setPassword("123456");
        newAccount.setUsername("ethan");
        newAccount.setEmail("deeplyocean@mail.com");

        AccountDto.Response result = accountService.save(newAccount);

        Assert.that("deeplyocean".equals(result.getUserId()), "SUCCESS");

        entityManager.clear();
    }
}
