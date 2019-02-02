package org.ocean.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EmailTest {

    @Test
    public void emailTest(){
        Email email = Email.builder().value("email@email.com").build();
        assertThat(email.getId()).isEqualTo("email");
        assertThat(email.getHost()).isEqualTo("email.com");
    }

    @Test
    public void emailQualsTest(){
        Email email1 = Email.builder().value("email@email.com").build();
        Email email2 = Email.builder().value("email@email.com").build();
        assertThat(email1).isEqualTo(email2);
        assertThat(email1.getValue()).isEqualTo(email2.getValue());
    }
}