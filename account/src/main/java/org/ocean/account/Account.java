package org.ocean.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Account {

    @GeneratedValue
    private Long id;
    private String userId;
    private String password;
    private String username;
    private String email;
}
