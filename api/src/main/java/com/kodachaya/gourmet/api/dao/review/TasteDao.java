package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.entity.review.TasteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteDao extends JpaRepository<TasteEntity, Integer> {
}
