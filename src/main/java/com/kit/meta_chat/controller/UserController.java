package com.kit.meta_chat.controller;


import com.kit.meta_chat.model.dto.UserDTO;
import com.kit.meta_chat.repository.UserRepository;
import com.kit.meta_chat.request.LoginRequest;
import com.kit.meta_chat.request.RegisterRequest;
import com.kit.meta_chat.request.RequestBinding;
import com.kit.meta_chat.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> readUser(@RequestBody @Validated LoginRequest loginRequest , BindingResult result){
        RequestBinding.CheckValidate(result);
        Object user = userService.readUser(loginRequest.getEmailOrUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody @Validated RegisterRequest registerRequest , BindingResult result){
        RequestBinding.CheckValidate(result);
        UserDTO user = userService.createUser(registerRequest.getEmail(),registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/checkRoler")
    public ResponseEntity<?> readUserAll(){;
        return ResponseEntity.ok("user");
    }


}
