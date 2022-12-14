package com.kit.meta_chat.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
public class Permission extends BaseEntity{

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
