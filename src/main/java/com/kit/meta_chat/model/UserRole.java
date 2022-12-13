package com.kit.meta_chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user_role")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {

    @Id
    @Column(name = "user_id",nullable = false)
    private Integer user_id;

    @Column(name = "role_id", nullable = false)
    private Integer role_id;
}
