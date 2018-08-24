package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.user.UserEntity;

public class UserMapper {

    public static UserModel map(UserEntity entity) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setIntroduce(entity.getIntroduce());
        // TODO set user profile
        user.setFollowingCount(entity.getFollowings().size());
        user.setFollowerCount(entity.getFollowers().size());
        user.setIsPublic(entity.isPublic());
        return user;
    }
}
