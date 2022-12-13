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
public class RegisterRequest {

    @Size(min = 10,max = 100,message = "Email is not in the correct format")
    @Email(message = "Email is not in the correct format")
    @NotBlank(message = "Email is requier")
    private String email;

    @Size(min = 10,max = 100,message = "Username is not in the correct format")
    @NotBlank(message = "Username is requier")
    private String username;

    @Size(min = 10,max = 50,message = "password is not in the correct format")
    @NotBlank(message = "Password is requier")
    private String password;
}
