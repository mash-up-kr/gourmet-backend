package com.kodachaya.gourmet.api.dao.wish;

import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WishDao extends JpaRepository<WishEntity, Integer> {

    Page<WishEntity> findAllByUserId(int userId, Pageable pageable);


    Optional<WishEntity> findByMenuId(int menuId);


    int countByUserId(int userId);


    Optional<WishEntity> findByReviewId(int reviewId);
}
