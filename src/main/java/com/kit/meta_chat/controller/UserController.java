package com.kit.meta_chat.controller;


import com.kit.meta_chat.message.SuccessMessage;
import com.kit.meta_chat.model.dto.UserDTO;
import com.kit.meta_chat.repository.UserRepository;
import com.kit.meta_chat.request.LoginRequest;
import com.kit.meta_chat.request.RegisterRequest;
import com.kit.meta_chat.request.RequestBinding;
import com.kit.meta_chat.responsive.BaseRespo;
import com.kit.meta_chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest , BindingResult result){
        RequestBinding.CheckValidate(result);
        Object user = userService.login(loginRequest.getEmailOrUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterRequest registerRequest , BindingResult result){
        RequestBinding.CheckValidate(result);
        UserDTO user = userService.register(registerRequest.getEmail(),registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/delete/{uuid}")
    @PreAuthorize("hasAnyAuthority('ADMIN_DELETE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable(required = true) String uuid){
        userService.deleteUser(uuid);
        return ResponseEntity.ok(BaseRespo.builder().code(-1).status("success").content(null).title(SuccessMessage.UPDATE_SUCCESS).build());
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAnyAuthority('ADMIN_CREATE_USER')")
    public ResponseEntity<?> createUser(@RequestBody @Validated RegisterRequest registerRequest , BindingResult result){
        RequestBinding.CheckValidate(result);
        UserDTO user = userService.register(registerRequest.getEmail(),registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(user);
    }


}
