package com.kodachaya.gourmet.api.service.user;


import com.kodachaya.gourmet.api.entity.user.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> find(int userId);

    Optional<UserEntity> get(String username);

    UserEntity save(String username, String password);

    UserEntity updateAdditionalInfo(int userId, String profileImageUrl, String introduce);

    boolean follow(int userId, int followUserId);

    boolean unfollow(int userId, int followingUserId);

}
