package com.kodachaya.gourmet.api.service.user;


import com.kodachaya.gourmet.api.entity.user.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> get(String username);

    Optional<UserEntity> save(String username, String password);

    Optional<UserEntity> updateAdditionalInfo(int userId, Optional<String> profileImageUrl, Optional<String> introduce);

    boolean follow(int userId, int followUserId);

    boolean unfollow(int userId, int followingUserId);

}
