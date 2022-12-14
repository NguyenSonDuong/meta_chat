package com.kit.meta_chat.mapping;

import com.kit.meta_chat.jwt.user_detail.UserPrincipal;
import com.kit.meta_chat.model.Sex;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.dto.UserDTO;

import java.util.HashSet;
import java.util.Set;

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

    public static UserPrincipal convertUserPrincipal(User user){
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles()) user.getRoles().forEach(r -> {
                authorities.add(r.getName());
                r.getPermissions().forEach(p -> authorities.add(p.getName()));
            });

            userPrincipal.setUuid(user.getUuid());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setEmail(user.getEmail());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
