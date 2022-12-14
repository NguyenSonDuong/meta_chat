package com.kit.meta_chat.repository;

import com.kit.meta_chat.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByToken(String token);

}