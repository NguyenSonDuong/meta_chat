package com.kit.meta_chat.controller.exception;


import com.kit.meta_chat.exception.UserException;
import com.kit.meta_chat.exception.ValidateExeption;
import com.kit.meta_chat.responsive.BaseRespo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(ValidateExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseRespo validateExceptionHandle(ValidateExeption validateExeption){
        BaseRespo baseRespo = new BaseRespo();
        baseRespo.setCode(-1);
        baseRespo.setContent(validateExeption.getContent());
        baseRespo.setTitle("Field wrong format");
        baseRespo.setStatus("error");
        return baseRespo;
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseRespo userExceptionHandle(UserException userException){
        BaseRespo baseRespo = new BaseRespo();
        baseRespo.setCode(-1);
        baseRespo.setContent(null);
        baseRespo.setTitle(userException.getMessage());
        baseRespo.setStatus("error");
        return baseRespo;
    }

}
