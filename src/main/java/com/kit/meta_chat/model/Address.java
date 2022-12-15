package com.kit.meta_chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

    @Column(name = "nation", length = 20)
    private String nation;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state", length = 20)
    private String state;

    @Column(name = "address_1", length = 50)
    private String addressOne;

    @Column(name = "address_2", length = 50)
    private String addressTwo;

    @OneToOne
    @JoinColumn(name = "user_info")
    private UserInfo userInfo;
}
