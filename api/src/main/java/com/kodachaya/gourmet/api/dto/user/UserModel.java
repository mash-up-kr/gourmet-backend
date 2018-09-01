package com.kodachaya.gourmet.api.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    private int id;
    private String username;
    private Optional<String> introduce;
    private Optional<String> profileImage;
    private Optional<Boolean> isPublic;
    private int followingCount;
    private int followerCount;
    private Optional<Boolean> isFollowing;
    private Optional<Integer> stampCount;
    private Optional<Integer> wishCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = Optional.ofNullable(introduce);
    }

    public Optional<String> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = Optional.ofNullable(profileImage);
    }

    public Optional<Integer> getStampCount() {
        return stampCount;
    }

    public void setStampCount(Integer stampCount) {
        this.stampCount = Optional.ofNullable(stampCount);
    }

    public Optional<Integer> getWishCount() {
        return wishCount;
    }

    public void setWishCount(Integer wishCount) {
        this.wishCount = Optional.ofNullable(wishCount);
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public Optional<Boolean> getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(Boolean isFollowing) {
        this.isFollowing = Optional.ofNullable(isFollowing);
    }

    public Optional<Boolean> getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = Optional.of(isPublic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                stampCount == userModel.stampCount &&
                wishCount == userModel.wishCount &&
                followingCount == userModel.followingCount &&
                followerCount == userModel.followerCount &&
                isFollowing == userModel.isFollowing &&
                isPublic == userModel.isPublic &&
                Objects.equals(username, userModel.username) &&
                Objects.equals(introduce, userModel.introduce) &&
                Objects.equals(profileImage, userModel.profileImage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, introduce, profileImage, stampCount, wishCount, followingCount, followerCount, isFollowing, isPublic);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", introduce='" + introduce + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", stampCount=" + stampCount +
                ", wishCount=" + wishCount +
                ", followingCount=" + followingCount +
                ", followerCount=" + followerCount +
                ", isFollowing=" + isFollowing +
                ", isPublic=" + isPublic +
                '}';
    }
}

