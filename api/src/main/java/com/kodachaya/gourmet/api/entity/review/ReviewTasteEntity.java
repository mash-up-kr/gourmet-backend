package com.kodachaya.gourmet.api.entity.review;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ReviewTaste")
public class ReviewTasteEntity {

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn(name = "tasteId", foreignKey = @ForeignKey(name = "FK_ReviewTaste_Taste"), nullable = false)
    private TasteEntity taste;


    @ManyToOne
    @JoinColumn(name = "reviewId", foreignKey = @ForeignKey(name = "FK_ReviewTaste_Review"), nullable = false)
    private ReviewEntity review;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TasteEntity getTaste() {
        return taste;
    }

    public void setTaste(TasteEntity taste) {
        this.taste = taste;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewTasteEntity that = (ReviewTasteEntity) o;
        return id == that.id &&
                Objects.equals(taste, that.taste) &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taste, review);
    }

    @Override
    public String toString() {
        return "ReviewTasteEntity{" +
                "id=" + id +
                ", taste=" + taste +
                ", review=" + review +
                '}';
    }
}
