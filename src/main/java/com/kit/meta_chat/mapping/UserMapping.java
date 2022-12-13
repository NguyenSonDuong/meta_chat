package com.kit.meta_chat.mapping;

import com.kit.meta_chat.model.Sex;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.dto.UserDTO;

public class UserMapping {
    public static UserDTO convertUserDTO(User user){
        UserDTO userdto = new UserDTO();
        userdto.setUser(user.getUsername());
        userdto.setUuid(user.getUuid());
        if(user.getSex() == Sex.MAN){
            userdto.setSex("man");
        }
        if(user.getSex() == Sex.WOMAN){
            userdto.setSex("woman");
        }
        if(user.getSex() == Sex.OTHER){
            userdto.setSex("other");
        }
        userdto.setEmail(user.getEmail());
        userdto.setRoles(user.getRoles());
        return userdto;
    }
}
