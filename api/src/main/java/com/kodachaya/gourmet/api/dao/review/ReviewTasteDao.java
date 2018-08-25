package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.entity.review.ReviewTasteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewTasteDao extends JpaRepository<ReviewTasteEntity, Integer> {
}
