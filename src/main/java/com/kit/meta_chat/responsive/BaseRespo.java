package com.kit.meta_chat.responsive;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseRespo {
    private String status;
    private String title;
    private int code;
    private Object content;
}
