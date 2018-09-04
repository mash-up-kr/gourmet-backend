package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.user.UserEntity;

public class UserMapper {


    public static UserModel map(UserEntity entity) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setProfileImage(entity.getProfile());
        user.setIntroduce(entity.getIntroduce());
        user.setFollowingCount(entity.getFollowings().size());
        user.setFollowerCount(entity.getFollowers().size());
        user.setIsPublic(entity.isPublic());
        return user;
    }


    public static UserModel map(UserEntity entity, int stampCount) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setProfileImage(entity.getProfile());
        user.setIntroduce(entity.getIntroduce());
        user.setFollowingCount(entity.getFollowings().size());
        user.setFollowerCount(entity.getFollowers().size());
        user.setIsPublic(entity.isPublic());
        user.setStampCount(stampCount);
        return user;
    }


    public static UserModel map(UserEntity entity, int stampCount, int wishCount) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setProfileImage(entity.getProfile());
        user.setIntroduce(entity.getIntroduce());
        user.setFollowingCount(entity.getFollowings().size());
        user.setFollowerCount(entity.getFollowers().size());
        user.setIsPublic(entity.isPublic());
        user.setStampCount(stampCount);
        user.setWishCount(wishCount);
        return user;
    }
}
