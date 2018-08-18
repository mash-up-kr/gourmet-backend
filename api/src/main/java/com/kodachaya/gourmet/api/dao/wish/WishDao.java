package com.kodachaya.gourmet.api.dao.wish;

import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishDao extends JpaRepository<WishEntity, Integer> {
}
