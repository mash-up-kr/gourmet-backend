package com.kodachaya.gourmet.api.service.wish;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WishService {

    Page<WishEntity> getWishes(UserEntity user, Pageable pageable);


    boolean deleteWish(int wishId);


    WishEntity makeWish(int userId, MenuEntity menu);


    WishEntity makeWish(int userId, int reviewId);


    Optional<WishEntity> bookmarkWish(int userId, int wishId);


    int getWishCount(int userId);
}
