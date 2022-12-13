package com.kit.meta_chat.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermission {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer role_id;

    @Column(name = "permission_id", nullable = false)
    private Integer permission_id;
}
