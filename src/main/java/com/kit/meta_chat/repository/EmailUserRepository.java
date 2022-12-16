package com.kit.meta_chat.repository;

import com.kit.meta_chat.model.EmailUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailUserRepository extends JpaRepository<EmailUser, Integer> {
}