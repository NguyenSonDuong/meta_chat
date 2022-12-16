package com.kit.meta_chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailUser extends BaseEntity {

    @Column(name = "confirm")
    private int confirm ;

    @Column(name = "confirmation_code", length = 5)
    private String confirmation_code ;

    @Column(name = "confirmation_exp")
    private Date confirmation_exp ;

    @Column(name = "type")
    private int type ;

    @Column(name = "user_id")
    private int user_id;

}
