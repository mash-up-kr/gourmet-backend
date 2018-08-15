package com.kodachaya.gourmet.api.service;


import com.kodachaya.gourmet.api.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> get(String username);

    Optional<UserEntity> save(String username, String password);
}
