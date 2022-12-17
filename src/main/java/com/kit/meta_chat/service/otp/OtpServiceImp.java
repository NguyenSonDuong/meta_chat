package com.kit.meta_chat.service.otp;

import com.kit.meta_chat.exception.UserException;
import com.kit.meta_chat.message.ErrorMessage;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.otp.OtpGeneral;
import com.kit.meta_chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtpServiceImp implements OtpService{


    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null)
            throw new UserException(ErrorMessage.ERROR_USER_NOT_EXIT);
        return user;
    }

    @Override
    public String getTemplate(OtpGeneral otpGeneral) {
        return null;
    }

    @Override
    public OtpGeneral generalCode(User user) {
        OtpGeneral otpGeneral = new OtpGeneral();
        




        return null;
    }
}
