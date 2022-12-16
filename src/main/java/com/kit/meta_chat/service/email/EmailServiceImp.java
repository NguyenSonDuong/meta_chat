package com.kit.meta_chat.service.email;

import com.kit.meta_chat.responsive.BaseRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImp implements EmailService{

    @Autowired
    private JavaMailSender emJavaMailSender;

    @Override
    public boolean sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyenduong08041999@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emJavaMailSender.send(message);
        return false;
    }

    @Override
    public BaseRespo sendOTP(String email) {
        return null;
    }
}
