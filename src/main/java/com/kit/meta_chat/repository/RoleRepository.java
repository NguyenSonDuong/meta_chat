package com.kit.meta_chat.repository;

import com.kit.meta_chat.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}