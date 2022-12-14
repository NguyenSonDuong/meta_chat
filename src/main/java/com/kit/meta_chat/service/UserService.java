package com.kit.meta_chat.service;


import com.kit.meta_chat.model.Role;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {

    public Object login(String emailOrUsername, String password);
    public UserDTO createUserInfo(String email ,String username, String password, int sex);
    public UserDTO createUser(String email, String username,String password, int sex, Set<Role> roleSet);
    public UserDTO register(String email ,String username, String password);
    public List<UserDTO> search(String uuid, String username, String email, int sex);
    public boolean updateUser(String uuid, User user);
    public boolean deleteUser(String uuid);

}
