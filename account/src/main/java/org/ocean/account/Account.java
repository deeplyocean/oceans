package org.ocean.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ocean.type.Email;
import org.ocean.type.Password;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Email email;
    @Embedded
    private Password password;
    private String accountName;

    @Builder
    public Account(Email email, Password password, String accountName){
        this.email = email;
        this.password = password;
        this.accountName = accountName;
    }

    public void updateAccount(Account account){
        this.email = account.getEmail();
        this.accountName = account.getAccountName();
    }

    public void updatePassword(Password password){
        this.password = password;
    }
}
