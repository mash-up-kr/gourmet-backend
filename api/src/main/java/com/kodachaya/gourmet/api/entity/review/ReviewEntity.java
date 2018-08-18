package com.kodachaya.gourmet.api.entity.review;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Review")
public class ReviewEntity {

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn(name = "stampId", foreignKey = @ForeignKey(name = "FK_Review_Stamp"), nullable = false)
    private StampEntity stamp;


    @ManyToOne
    @JoinColumn(name = "menuId", foreignKey = @ForeignKey(name = "FK_Review_Menu"), nullable = false)
    private MenuEntity menu;


    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_Review_User"), nullable = false)
    private UserEntity user;


    @Column(name = "comment", nullable = false)
    private String comment;


    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;


    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="reviewId")
    private Set<ReviewMenuImageEntity> images;


    @PrePersist
    public void persist() {
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StampEntity getStamp() {
        return stamp;
    }

    public void setStamp(StampEntity stamp) {
        this.stamp = stamp;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<ReviewMenuImageEntity> getImages() {
        return images;
    }

    public void setImages(Set<ReviewMenuImageEntity> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return id == that.id &&
                Objects.equals(stamp, that.stamp) &&
                Objects.equals(menu, that.menu) &&
                Objects.equals(user, that.user) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stamp, menu, user, comment, createdAt, images);
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id=" + id +
                ", stamp=" + stamp +
                ", menu=" + menu +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", images=" + images +
                '}';
    }
}
