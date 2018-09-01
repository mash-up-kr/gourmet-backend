package com.kodachaya.gourmet.api.dao.review;

import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.entity.review.TasteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteDao extends JpaRepository<TasteEntity, Integer> {

    TasteEntity findByTaste(Taste taste);

}
