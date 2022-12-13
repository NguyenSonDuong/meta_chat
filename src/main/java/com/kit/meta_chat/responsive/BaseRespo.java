package com.kit.meta_chat.responsive;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespo {
    private String status;
    private String title;
    private int code;
    private Object content;
}
