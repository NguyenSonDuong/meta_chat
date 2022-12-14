package com.kit.meta_chat.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequest {
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

    private String role;
    private String[] permisstion;
}
