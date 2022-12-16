package com.kit.meta_chat.service.otp;

import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.otp.OtpGeneral;
import org.springframework.stereotype.Service;

@Service
public interface OtpService {
    public User getUserByEmail(String email);
    public String getTemplate(OtpGeneral otpGeneral);
    public OtpGeneral generalCode(User user);
}
