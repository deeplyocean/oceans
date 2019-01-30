package org.ocean.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EmailTest {

    @Test
    public void emailTest() {
        Email email = Email.builder().value("email@email.com").build();
        assertThat(email.getId()).isEqualTo("email");
        assertThat(email.getHost()).isEqualTo("email.com");
    }
}