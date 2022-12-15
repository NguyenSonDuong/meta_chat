package com.kit.meta_chat.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImp implements EmailService{

    @Autowired
    private JavaMailSender emJavaMailSender;

    @Override
    public boolean sendEmail(String to, String subject, String text) {
        return false;
    }
}
