package com.kodachaya.gourmet.api.service.newsfeed;

import com.kodachaya.gourmet.api.dao.review.ReviewDao;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsfeedServiceImpl implements NewsfeedService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Page<ReviewEntity> get(int userId, Pageable pageable) {
        return reviewDao.findAll(pageable);
    }
}
