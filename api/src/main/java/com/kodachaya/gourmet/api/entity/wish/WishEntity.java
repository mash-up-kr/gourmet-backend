package com.kodachaya.gourmet.api.entity.wish;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Wish")
public class WishEntity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "menuId", foreignKey = @ForeignKey(name = "FK_Wish_Menu"), nullable = false)
    private MenuEntity menu;


    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_Wish_User"), nullable = false)
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "reviewId", foreignKey = @ForeignKey(name = "FK_WishReview_Review"))
    private ReviewEntity review;


    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;


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

    public ReviewEntity getReview() {
        return review;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WishEntity{" +
                "id=" + id +
                ", menu=" + menu +
                ", user=" + user +
                ", review=" + review +
                ", createdAt=" + createdAt +
                '}';
    }
}
