package org.ocean.account;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.ocean.type.Email;
import org.ocean.type.Password;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class AccountDto {

    private AccountDto(){
        throw new IllegalStateException(this.getClass().getName());
    }

    @Getter @Setter @Builder
    public static class Create {
        @Valid
        private Email email;
        @Valid
        private Password password;
        @NotBlank
        private String accountName;
        private List<AccountRoles> roles;
    }

    @Getter @Setter @EqualsAndHashCode(of = "id")
    public static class Response {
        private Long id;
        private Email email;
        private String accountName;
        private List<AccountRoles> roles;
    }

    @Getter @Setter @Builder
    public static class Update {
        @Valid
        private Email email;
        @Valid
        private Password password;
        @NotBlank
        private String accountName;
        private List<AccountRoles> roles;
    }
}
