package com.kodachaya.gourmet.api.service.newsfeed;

import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NewsfeedService {

    Page<ReviewEntity> get(int userId, Pageable pageable);

}
