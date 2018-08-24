package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<ReviewEntity, Integer> {
}
