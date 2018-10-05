package com.kodachaya.gourmet.api.dao.wish;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WishDao extends JpaRepository<WishEntity, Integer> {

    Page<WishEntity> findAllByUser(UserEntity user, Pageable pageable);


    Optional<WishEntity> findByMenu(MenuEntity menu);


    @Query(value = "SELECT count(*) from Wish WHERE user_id = :userId", nativeQuery = true)
    int getCountByUserId(@Param("userId") int userId);


    boolean existsByMenu(MenuEntity menu);


    Optional<WishEntity> findByReview(ReviewEntity review);
}
