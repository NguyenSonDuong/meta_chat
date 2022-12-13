package com.kit.meta_chat.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateExeption extends RuntimeException{
    private Object content;
    public ValidateExeption(String message,Object content) {
        super(message);
        this.content = content;
    }
}
