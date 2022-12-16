package com.kit.meta_chat.service.email;

import com.kit.meta_chat.responsive.BaseRespo;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    boolean sendEmail(String to, String subject, String text);

    BaseRespo sendOTP(String email);
}
