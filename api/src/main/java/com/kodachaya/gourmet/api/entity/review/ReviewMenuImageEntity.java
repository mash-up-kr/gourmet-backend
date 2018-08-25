package com.kodachaya.gourmet.api.entity.review;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ReviewMenu")
public class ReviewMenuImageEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "reviewId", nullable = false)
    private int reviewId;

    private String imageUrl;

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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewMenuImageEntity that = (ReviewMenuImageEntity) o;
        return id == that.id &&
                reviewId == that.reviewId &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewId, imageUrl, createdAt);
    }

    @Override
    public String toString() {
        return "ReviewMenuImageEntity{" +
                "id=" + id +
                ", reviewId=" + reviewId +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
