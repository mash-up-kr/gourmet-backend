package com.kodachaya.gourmet.api.service.user;

import com.kodachaya.gourmet.api.dao.user.UserDao;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Optional<UserEntity> find(int userId) {
        return dao.findById(userId);
    }

    @Override
    public Optional<UserEntity> get(String username) {
        return dao.findByUsername(username);
    }


    @Override
    public Optional<UserEntity> save(String username, String password) {
        // check already username exists.
        if (dao.findByUsername(username).isPresent()) {
            throw new BadRequestException("Already Exists");
        }

        // save to database
        return Optional.of(dao.save(new UserEntity(username, passwordEncoder.encode(password))));
    }

    @Override
    public Optional<UserEntity> updateAdditionalInfo(int userId, Optional<String> profileImageUrl, Optional<String> introduce) {
        return Optional.empty();
    }

    @Override
    public boolean follow(int userId, int followUserId) {
        return false;
    }

    @Override
    public boolean unfollow(int userId, int followingUserId) {
        return false;
    }
}
