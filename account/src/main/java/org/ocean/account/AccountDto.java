package org.ocean.account;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountDto {

    @Data
    public static class Create {

        @NotEmpty
        @Size(min=4, max=20)
        private String userId;

        @NotEmpty
        @Size(min=6, max=40)
        private String password;

        @NotEmpty
        @Size(min=4, max=20)
        private String username;

        private String email;
    }

    @Data
    public static class Response{

        private String userId;
        private String username;
        private String email;

    }
}
