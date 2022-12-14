package com.kit.meta_chat.repository;

import com.kit.meta_chat.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}