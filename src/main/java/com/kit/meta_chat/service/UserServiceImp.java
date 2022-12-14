package com.kit.meta_chat.service;


import com.kit.meta_chat.exception.UserException;
import com.kit.meta_chat.jwt.JwtUtil;
import com.kit.meta_chat.jwt.user_detail.UserPrincipal;
import com.kit.meta_chat.mapping.SexMapping;
import com.kit.meta_chat.mapping.UserMapping;
import com.kit.meta_chat.model.Permission;
import com.kit.meta_chat.model.Role;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.dto.UserDTO;
import com.kit.meta_chat.model.token.Token;
import com.kit.meta_chat.repository.RoleRepository;
import com.kit.meta_chat.repository.TokenRepository;
import com.kit.meta_chat.repository.UserRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenService;

    private JwtUtil jwtUtil = new JwtUtil();


    @Override
    public Object readUser(String emailOrUsername, String password) {
        String regex = "^(\\S+)@([\\S]+)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(emailOrUsername);
        Token token = new Token();
        if(!matcher.find()){
            User user = userRepository.findByUsername(emailOrUsername);
            if(user == null)
                throw new UserException("Account isn't exits");
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                UserPrincipal userPrincipal = UserMapping.convertUserPrincipal(user);
                token.setToken(jwtUtil.generateToken(userPrincipal));
                token.setExpDate(jwtUtil.generateExpirationDate());
                tokenService.save(token);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("info",UserMapping.convertUserDTO(user));
                jsonObject.put("token", token.getToken());
                jsonObject.put("exp_date", token.getExpDate());
                return jsonObject;
            }else{
                throw new UserException("Username or password were wrong");
            }

        }else{
            User user = userRepository.findByEmail(emailOrUsername);
            if(user == null)
                throw new UserException("Account isn't exits");
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                UserPrincipal userPrincipal = UserMapping.convertUserPrincipal(user);
                token.setToken(jwtUtil.generateToken(userPrincipal));
                token.setExpDate(jwtUtil.generateExpirationDate());
                tokenService.save(token);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("info",UserMapping.convertUserDTO(user));
                jsonObject.put("token", token.getToken());
                jsonObject.put("exp_date", token.getExpDate());
                return jsonObject;
            }else{
                throw new UserException("Username or password were wrong");
            }

        }
    }

    @Override
    public UserDTO createUserInfo(String email, String username, String password, int sex) {
        if(userRepository.existsByUsernameOrEmail(username,email))
            throw new UserException("Username or email is exits");
        String role = "USER";
        Role rol = new Role(role, (role.equals("USER") ? "Người dùng" : (role.equals("ADMIN") ? "Quản trị viên" : "Khách")));
        rol.setPermissions(new HashSet<>());
        rol.getPermissions().addAll(GetPremission(role));
        String passwordEncrypt = new BCryptPasswordEncoder().encode(password);
        User user = User.builder()
                .username(username)
                .password(passwordEncrypt)
                .email(email)
                .build();
        user.setRoles(new HashSet<>());
        user.getRoles().add(rol);

        if(sex>0)
            user.setSex(SexMapping.sexMapping(sex));

        User userSave = userRepository.saveAndFlush(user);

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

    public Set<Permission> GetPremission(String role){
        Set<Permission> permissions = new HashSet<Permission>();
        if(role.equals("USER")){
            permissions.add(new Permission("CREATE_USER","Tạo tài khoản"));
            permissions.add(new Permission("READ_USER","Đọc thông tin tài khoản"));
            permissions.add(new Permission("UPDATE_USER","Sửa thông tài khoản"));
            permissions.add(new Permission("MESSAGE","Gửi tin nhắn"));
        }
        if(role.equals("ADMIN")){
            permissions.add(new Permission("CREATE_USER","Tạo tài khoản"));
            permissions.add(new Permission("READ_USER","Đọc thông tin tài khoản"));
            permissions.add(new Permission("UPDATE_USER","Sửa thông tài khoản"));
            permissions.add(new Permission("DELETE_USER","Xóa tài khoản"));
            permissions.add(new Permission("MESSAGE","Gửi tin nhắn"));
            permissions.add(new Permission("CHANGE_ROLE_USER","Thay đổi quyền người dùng"));
        }
        if(role.equals("CUSTOMER")){
            permissions.add(new Permission("READ_USER","Đọc thông tin tài khoản"));
            permissions.add(new Permission("MESSAGE","Gửi tin nhắn"));
        }
        return  permissions;
    }
}
