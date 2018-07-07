package com.kodachaya.gourmet.api.dao;

import com.kodachaya.gourmet.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT * FROM User WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<UserEntity> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
