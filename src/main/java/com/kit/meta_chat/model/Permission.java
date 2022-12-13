package com.kit.meta_chat.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity{

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

}
