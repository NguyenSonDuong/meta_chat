package com.kit.meta_chat.controller;


import com.kit.meta_chat.service.email.EmailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mail")
public class OtpController {

    @Autowired
    EmailService emailService;

    @GetMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestParam String email){

    }
}
