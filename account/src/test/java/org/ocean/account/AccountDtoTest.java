package org.ocean.account;

import org.junit.Test;
import org.ocean.type.Email;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDtoTest {

    @Test
    public void accountDtoEqualsTest() {
        AccountDto.Response dto1 = new AccountDto.Response();
        dto1.setId(0L);
        dto1.setEmail(Email.builder().value("test@email.com").build());
        dto1.setAccountName("test");

        AccountDto.Response dto2 = new AccountDto.Response();
        dto2.setId(0L);
        dto2.setEmail(Email.builder().value("test@email.com").build());
        dto2.setAccountName("test");

        assertThat(dto1).isEqualTo(dto2);
    }
}
