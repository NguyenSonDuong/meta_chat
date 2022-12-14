package com.kit.meta_chat.repository;


import com.kit.meta_chat.model.Sex;
import com.kit.meta_chat.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Integer> {
    long deleteByUuid(String uuid);
    boolean existsByUuid(String uuid);
    User findByEmailAndPassword(String email, String password);
    User findByUsernameAndPassword(String username, String password);
    boolean existsByUsernameOrEmail(String username, String email);
    List<User> findByUsernameOrEmailAndUuidAndSexAllIgnoreCaseOrderByUsernameAsc(@Nullable String username, @Nullable String email, @Nullable String uuid, @Nullable Sex sex);
    List<User> findByUuidOrEmailOrUsernameOrSex(String uuid, String email, String username, Sex sex);
    List<User> findByUuidOrEmailOrUsername(String uuid, String email, String username);

    User findByUsername(String emailOrUsername);

    User findByEmail(String emailOrUsername);
}