package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.entity.review.StampEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampDao extends JpaRepository<StampEntity, Integer> {

    StampEntity findByExpression(Stamp stamp);
}
