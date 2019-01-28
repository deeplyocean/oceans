package org.ocean.account;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountDto {

    @Data
    public static class Create {
        @NotBlank
        @Size(min=6, max=20)
        private String accountNo;
        @NotBlank
        @Size(min=8, max=20)
        private String password;
        @NotBlank
        private String accountName;

        private String email;
    }

    @Data
    public static class Update {
        @NotBlank
        @Size(min=8, max=20)
        private String password;
        @NotBlank
        private String accountName;

        private String email;
    }
}
