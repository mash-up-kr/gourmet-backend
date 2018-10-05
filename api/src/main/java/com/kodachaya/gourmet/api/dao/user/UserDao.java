package com.kodachaya.gourmet.api.dao.user;


import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

}
