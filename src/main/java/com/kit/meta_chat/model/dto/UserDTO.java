package com.kit.meta_chat.model.dto;

import com.kit.meta_chat.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String uuid;

    private String email;

    private String user;

    private String sex;

    private Set<Role> roles;
}
