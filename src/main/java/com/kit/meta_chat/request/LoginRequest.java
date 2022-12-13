package com.kit.meta_chat.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username or email not blank")
    @Size(min = 10,max = 100,message = "Email or username is not in the correct format")
    private String emailOrUsername;


    @Size(min = 10,max = 50,message = "password is not in the correct format")
    @NotBlank(message = "Password is requier")
    private String password;
}
