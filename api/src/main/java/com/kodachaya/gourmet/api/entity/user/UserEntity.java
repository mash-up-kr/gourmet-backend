package com.kodachaya.gourmet.api.entity.user;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isPublic")
    private boolean isPublic;

    @Column(name = "introduce", nullable = true)
    private String introduce;

    @Column(name = "profile", nullable = true)
    private String profile;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserFollow",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "followingId"))
    private List<UserEntity> followings;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserFollow",
            joinColumns = @JoinColumn(name = "followingId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<UserEntity> followers;


    public UserEntity() {}


    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @PrePersist
    public void persist() {
        this.createdAt = LocalDateTime.now();
        this.isPublic = true;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() { return profile; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserEntity> getFollowings() {
        return followings;
    }

    public void setFollowings(List<UserEntity> followings) {
        this.followings = followings;
    }

    public List<UserEntity> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserEntity> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isPublic, introduce, profile, createdAt, followings, followers);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isPublic=" + isPublic +
                ", introduce='" + introduce + '\'' +
                ", profile='" + profile + '\'' +
                ", createdAt=" + createdAt +
                ", followings=" + followings +
                ", followers=" + followers +
                '}';
    }
}
