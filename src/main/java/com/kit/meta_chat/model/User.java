package com.kit.meta_chat.model;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Data
@Transactional
@Table(name = "user", indexes = {
        @Index(name = "user_index", columnList = "username"),
        @Index(name = "uuid_index", columnList = "uuid"),
        @Index(name = "idx_user_email", columnList = "email")
})
public class User extends BaseEntity {

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "sex")
    private Sex sex;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    })
    private Set<Role> roles;

    @PrePersist
    public void preCreateUser() {
        this.uuid = UUID.randomUUID().toString();
        this.setCreate_at(new Date());
        this.setUpdate_at(new Date());
    }

    @PreUpdate
    public void preUpdateUser() {
        this.setUpdate_at(new Date());
    }

}
