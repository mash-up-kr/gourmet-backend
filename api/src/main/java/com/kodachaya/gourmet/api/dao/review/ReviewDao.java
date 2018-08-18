package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<ReviewModel, Integer> {
}
