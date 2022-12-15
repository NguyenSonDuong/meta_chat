package com.kit.meta_chat.repository;


import com.kit.meta_chat.model.Sex;
import com.kit.meta_chat.model.User;
import com.kit.meta_chat.model.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("update User u set u.userInfo = ?1 where u.uuid = ?2")
    int updateUserInfoByUuid(UserInfo userInfo, String uuid);
    User findByUuid(String uuid);
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