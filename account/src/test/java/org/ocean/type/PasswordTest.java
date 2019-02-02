package org.ocean.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordTest {

    @Test
    public void passwordTest(){
        Password password = Password.builder().value("password").build();
        assertThat(password.getValue()).isEqualTo("password");
    }

    @Test
    public void passwordEqualsTest(){
        Password password1 = Password.builder().value("password").build();
        Password password2 = Password.builder().value("password").build();
        assertThat(password1).isEqualTo(password2);
    }
}
