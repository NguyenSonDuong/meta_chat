package com.kit.meta_chat.service;


import com.kit.meta_chat.exception.UserException;
import com.kit.meta_chat.jwt.JwtUtil;
import com.kit.meta_chat.jwt.user_detail.UserPrincipal;
import com.kit.meta_chat.mapping.SexMapping;
import com.kit.meta_chat.mapping.UserInfoMapping;
import com.kit.meta_chat.mapping.UserMapping;
import com.kit.meta_chat.message.ErrorMessage;
import com.kit.meta_chat.model.*;
import com.kit.meta_chat.model.key.RoleKey;
import com.kit.meta_chat.model.dto.UserDTO;
import com.kit.meta_chat.model.token.Token;
import com.kit.meta_chat.repository.RoleRepository;
import com.kit.meta_chat.repository.TokenRepository;
import com.kit.meta_chat.repository.UserRepository;
import com.kit.meta_chat.request.UpdateUserInforRequest;
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
    public Object login(String emailOrUsername, String password) {
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
    public UserDTO createUser(String email, String username, String password) {
        if(userRepository.existsByUsernameOrEmail(username,email))
            throw new UserException("Username or email is exits");
        String role = "USER";
        Role rol = new Role(role, (role.equals("USER") ? "Người dùng" : (role.equals("ADMIN") ? "Quản trị viên" : "Khách")));
        rol.setPermissions(new HashSet<>());
        rol.getPermissions().addAll(GetPremission(role));
        Set<Role> roles = new HashSet<>();
        roles.add(rol);



        return createUser(email,username,password,-1,roles);
    }

    @Override
    public UserDTO createUser(String email, String username, String password, int sex, Set<Role> roleSet) {
        if(userRepository.existsByUsernameOrEmail(username,email))
            throw new UserException("Username or email is exits");
        String passwordEncrypt = new BCryptPasswordEncoder().encode(password);
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(new Address());
        User user = User.builder()
                .username(username)
                .password(passwordEncrypt)
                .email(email)
                .userInfo(userInfo)
                .build();
        user.setRoles(roleSet);
        User userSave = userRepository.saveAndFlush(user);
        if(userSave == null)
            throw new UserException("Something were wrong");
        return UserMapping.convertUserDTO(userSave);
    }

    @Override
    public UserDTO register(String email, String username, String password) {
        return createUser(email,username,password);
    }

    @Override
    public Object forgotPassword(String email) {

        return null;
    }

    @Override
    public List<UserDTO> search(String uuid, String username, String email, int sex) {

        return null;
    }

    @Override
    public boolean updateUser(String uuid, User user) {
        return false;
    }

    @Override
    public boolean updateUserInfo(String uuid, UpdateUserInforRequest userInfo) {
//        User user = userRepository.findByUuid(uuid);
        UserInfo userInfo1 = UserInfoMapping.requestMapping(userInfo);
        int user = userRepository.updateUserInfoByUuid(userInfo1,uuid);
        if(user<0)
            throw new UserException("Update user infor false");
        return false;
    }

    @Override
    public boolean deleteUser(String uuid) {
        if(userRepository.existsByUuid(uuid))
        {
            if(userRepository.deleteByUuid(uuid) > 0){
                return true;
            }else{
                throw new UserException(ErrorMessage.ERROR);
            }
        }else {
            throw new UserException(ErrorMessage.ERROR_USER_NOT_EXIT);
        }
    }

    public Set<Permission> GetPremission(String role){
        Set<Permission> permissions = new HashSet<Permission>();
        if(role.equals("USER")){
            permissions.add(new Permission(RoleKey.USER_READ_USER,"Đọc thông tin tài khoản"));
            permissions.add(new Permission(RoleKey.USER_UPDATE_USER,"Sửa thông tài khoản"));
            permissions.add(new Permission(RoleKey.USER_MESSAGE,"Gửi tin nhắn"));
        }
        if(role.equals("ADMIN")){
            permissions.add(new Permission(RoleKey.ADMIN_CREATE_USER,"Tạo tài khoản"));
            permissions.add(new Permission(RoleKey.ADMIN_READ_USER,"Đọc thông tin tài khoản"));
            permissions.add(new Permission(RoleKey.ADMIN_UPDATE_USER,"Sửa thông tài khoản"));
            permissions.add(new Permission(RoleKey.ADMIN_DELETE_USER,"Xóa tài khoản"));
            permissions.add(new Permission(RoleKey.ADMIN_MESSAGE,"Gửi tin nhắn"));
            permissions.add(new Permission(RoleKey.ADMIN_CHANGE_ROLE_USER,"Thay đổi quyền người dùng"));
        }
        if(role.equals("CUSTOMER")){
            permissions.add(new Permission(RoleKey.CUSTOMER_READ_USER,"Đọc thông tin tài khoản"));
            permissions.add(new Permission(RoleKey.CUSTOMER_MESSAGE,"Gửi tin nhắn"));
        }
        return  permissions;
    }
}
