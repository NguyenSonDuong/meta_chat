package com.kit.meta_chat.service;


import com.kit.meta_chat.exception.UserException;
import com.kit.meta_chat.mapping.SexMapping;
import com.kit.meta_chat.mapping.UserMapping;
import com.kit.meta_chat.model.Role;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.dto.UserDTO;
import com.kit.meta_chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO readUser(String emailOrUsername, String password) {
        String passwordEncrypt = new BCryptPasswordEncoder().encode(password);
        String regex = "^(\\S+)@([\\S]+)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(emailOrUsername);
        if(!matcher.find()){
            User user = userRepository.findByUsername(emailOrUsername);
            if(user == null)
                throw new UserException("Account isn't exits");
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                return UserMapping.convertUserDTO(user);
            }else{
                throw new UserException("Username or password were wrong");
            }

        }else{
            User user = userRepository.findByEmail(emailOrUsername);
            if(user == null)
                throw new UserException("Account isn't exits");
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                return UserMapping.convertUserDTO(user);
            }else{
                throw new UserException("Username or password were wrong");
            }

        }
    }

    @Override
    public UserDTO createUserInfo(String email, String username, String password, int sex) {
        if(userRepository.existsByUsernameOrEmail(username,email))
            throw new UserException("Username or email is exits");

        String passwordEncrypt = new BCryptPasswordEncoder().encode(password);

        User user = User.builder()
                .username(username)
                .password(passwordEncrypt)
                .email(email)
                .build();
//        user.setUserRoles(new HashSet<>());
//        user.getUserRoles().add(new UserRole("user",""))
        user.setRoles(new HashSet<>());
//        user.getRoles().add(new Role("user","nguoi dung"));
//        user.getRoles().add();
        if(sex>0)
            user.setSex(SexMapping.sexMapping(sex));

        User userSave = userRepository.save(user);

        if(userSave == null)
            throw new UserException("Something were wrong");

        return UserMapping.convertUserDTO(userSave);
    }

    @Override
    public UserDTO createUser(String email, String username, String password) {
        return createUserInfo(email,username,password,-1);
    }

    @Override
    public List<UserDTO> search(String uuid, String username, String email, int sex) {
        List<User> users = null;
        users = userRepository.findByUsernameOrEmailAndUuidAndSexAllIgnoreCaseOrderByUsernameAsc(username,email,uuid, SexMapping.sexMapping(sex));
        List<UserDTO> userDTOS  = new ArrayList<>();
        for (User user :
                users) {
            userDTOS.add(UserMapping.convertUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public boolean updateUser(String uuid, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String uuid) {
        return false;
    }
}
