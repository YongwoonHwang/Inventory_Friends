package SpringSecurityExample.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String userID;

    @NotEmpty
    private String password;
}
