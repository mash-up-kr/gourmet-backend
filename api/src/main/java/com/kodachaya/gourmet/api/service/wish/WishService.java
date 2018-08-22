package com.kodachaya.gourmet.api.service.wish;

import com.kodachaya.gourmet.api.entity.wish.WishEntity;

import java.util.List;
import java.util.Optional;

public interface WishService {

    List<WishEntity> getWishes(int userId, int cursor, int count);


    boolean deleteWish(int userId, int wishId);


    Optional<WishEntity> bookmarkWish(int userId, int wishId);
}
