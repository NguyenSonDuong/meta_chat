package com.kit.meta_chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {

    @Column(name = "fullname", length = 100)
    private String fullname;
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @OneToOne(mappedBy = "userInfo",cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "hobbies", length = 100)
    private String hobbies;
    @Column(name = "work", length = 100)
    private String work;

    @Column(name = "school", length = 255)
    private String school;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
