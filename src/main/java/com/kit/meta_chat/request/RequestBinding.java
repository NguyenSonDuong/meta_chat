package com.kit.meta_chat.request;


import com.kit.meta_chat.exception.ValidateExeption;
import com.kit.meta_chat.message.ErrorMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class RequestBinding {
    public static boolean CheckValidate(BindingResult result){
        if(result.hasErrors()){
            List<String> message = new ArrayList<>();
            for (FieldError objec: result.getFieldErrors()) {
                message.add(objec.getDefaultMessage());
            }
            throw new ValidateExeption(ErrorMessage.ERROR_MISSING_FIELD,message);
        }else{
            return false;
        }
    }
}
