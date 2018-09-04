package com.kodachaya.gourmet.api.service.review;

import com.kodachaya.gourmet.api.dao.restaurant.MenuDao;
import com.kodachaya.gourmet.api.dao.review.ReviewDao;
import com.kodachaya.gourmet.api.dao.review.ReviewTasteDao;
import com.kodachaya.gourmet.api.dao.review.StampDao;
import com.kodachaya.gourmet.api.dao.review.TasteDao;
import com.kodachaya.gourmet.api.dao.user.UserDao;
import com.kodachaya.gourmet.api.dao.wish.WishDao;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.review.StampEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private ReviewDao reviewDao;


    @Autowired
    private StampDao stampDao;


    @Autowired
    private TasteDao tasteDao;


    @Autowired
    private ReviewTasteDao reviewTasteDao;


    @Autowired
    private MenuDao menuDao;


    @Autowired
    private UserDao userDao;


    @Autowired
    private WishDao wishDao;


    @Override
    public Page<ReviewEntity> getReviews(UserEntity author, Pageable pageable) {
        return reviewDao.findAllByAuthor(author, pageable);
    }

    @Override
    public Optional<ReviewEntity> getReview(int reviewId) {
        return reviewDao.findById(reviewId);
    }

    @Override
    @Transactional
    public ReviewEntity create(int userId, int menuId, List<Taste> tastes, String comment, Stamp stamp) {
        UserEntity author = userDao.findById(userId).orElseThrow(() -> new NotFoundException("Not Found User"));
        MenuEntity menu = menuDao.findById(menuId).orElseThrow(() -> new NotFoundException("Not Found Menu"));
        StampEntity stampEntity = stampDao.findByExpression(stamp);

        wishDao.findByMenu(menu).ifPresent(wishDao::delete);

        ReviewEntity review = new ReviewEntity();
        review.setAuthor(author);
        review.setComment(comment);
        review.setMenu(menu);
        review.setStamp(stampEntity);
        return reviewDao.save(review);

//        ReviewEntity savedReview = reviewDao.save(review);
//
//        tastes.stream()
//                .map(tasteDao::findByTaste)
//                .forEach(entity -> {
//                    ReviewTasteEntity reviewTasteEntity = new ReviewTasteEntity();
//                    reviewTasteEntity.setReview(savedReview);
//                    reviewTasteEntity.setTaste(entity);
//                    reviewTasteDao.save(reviewTasteEntity);
//                });
//        return reviewDao.findById(savedReview.getId()).orElse(savedReview);
    }

    @Override
    public ReviewEntity update(int userId, int reviewId, List<String> imageUrls) {
        return null;
    }


    @Override
    public int getReviewCount(int userId) {
        return reviewDao.getCountByAuthor(userId);
    }
}
