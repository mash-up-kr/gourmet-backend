package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewDao extends JpaRepository<ReviewEntity, Integer> {

    Page<ReviewEntity> findAllByAuthor(UserEntity author, Pageable pageable);

    Page<ReviewEntity> findAll(Pageable pageable);

    @Query(value = "SELECT count(*) from Review WHERE user_id = :userId", nativeQuery = true)
    int getCountByAuthor(@Param("userId") int userId);

}
