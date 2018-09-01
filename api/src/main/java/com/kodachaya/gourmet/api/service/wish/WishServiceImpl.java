package com.kodachaya.gourmet.api.service.wish;

import com.kodachaya.gourmet.api.dao.review.ReviewDao;
import com.kodachaya.gourmet.api.dao.user.UserDao;
import com.kodachaya.gourmet.api.dao.wish.WishDao;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import com.kodachaya.gourmet.api.exception.AlreadyExistsException;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class WishServiceImpl implements WishService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WishDao wishDao;

    @Autowired
    private ReviewDao reviewDao;


    @Override
    public Page<WishEntity> getWishes(UserEntity user, Pageable pageable) {
        return wishDao.findAllByUser(user, pageable);
    }


    @Transactional
    @Override
    public boolean deleteWish(int wishId) {
        WishEntity wish = wishDao.findById(wishId).orElseThrow(() -> new NotFoundException("Not Found Wish"));
        wishDao.delete(wish);
        return true;
    }

    @Transactional
    @Override
    public WishEntity makeWish(int userId, MenuEntity menu) {
        if (wishDao.findByMenu(menu).isPresent()) {
            throw new AlreadyExistsException("Already wished.");
        }

        WishEntity wish = new WishEntity();
        wish.setUser(userDao.findById(userId).orElseThrow(() -> new NotFoundException("Not Found User")));
        wish.setMenu(menu);
        return wishDao.save(wish);
    }

    @Transactional
    @Override
    public WishEntity makeWish(int userId, int reviewId) {
        ReviewEntity review = reviewDao.findById(reviewId).orElseThrow(() -> new NotFoundException("Not Found Review"));
        if (wishDao.findByReview(review).isPresent()) {
            throw new AlreadyExistsException("Already wished.");
        }

        WishEntity wish = new WishEntity();
        wish.setUser(userDao.findById(userId).orElseThrow(() -> new NotFoundException("Not Found User")));
        wish.setMenu(review.getMenu());
        wish.setReview(review);
        return wishDao.save(wish);
    }

    @Override
    public Optional<WishEntity> bookmarkWish(int userId, int wishId) {
        return Optional.empty();
    }

    @Override
    public int getWishCount(int userId) {
        return wishDao.getCountByUserId(userId);
    }
}
