package org.ocean.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDtoTest {

    @Test
    public void accountDtoTest() {
       AccountDto.Response dto1 = new AccountDto.Response();
       dto1.setId(0L);

       AccountDto.Response dto2 = new AccountDto.Response();
       dto2.setId(0L);

       assertThat(dto1).isEqualTo(dto2);
    }
}
