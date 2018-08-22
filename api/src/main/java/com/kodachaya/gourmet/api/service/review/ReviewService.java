package com.kodachaya.gourmet.api.service.review;

import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewService {


    List<ReviewEntity> getReviews(int userId, int cursor, int count);



    Optional<ReviewEntity> getReview(int reviewId);



    boolean wishReview(int userId, int reviewId);



    Optional<ReviewEntity> create(int userId, int menuId, List<Taste> tastes, String comment, Stamp stamp);



    Optional<ReviewEntity> update(int reviewId, List<String> imageUrls);

}
