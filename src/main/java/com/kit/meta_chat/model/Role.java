package com.kit.meta_chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany
    @JoinTable(name = "permission", joinColumns = {
            @JoinColumn(name = "role_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "permission_id")
    })
    private Set<Permission> permissions;

    @PrePersist
    public void preCreate(){
        this.setCreate_at(new Date());
        this.setUpdate_at(new Date());
    }

}
