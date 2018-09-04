package com.kodachaya.gourmet.api.service.review;

import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {


    Page<ReviewEntity> getReviews(UserEntity user, Pageable pageable);


    Optional<ReviewEntity> getReview(int reviewId);


    int getReviewCount(int userId);


    ReviewEntity create(int userId, int menuId, List<Taste> tastes, String comment, Stamp stamp);


    ReviewEntity update(int userId, int reviewId, List<String> imageUrls);

}
