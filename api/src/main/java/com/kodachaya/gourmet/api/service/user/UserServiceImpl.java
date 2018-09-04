package com.kodachaya.gourmet.api.service.user;

import com.kodachaya.gourmet.api.dao.user.UserDao;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.NotFoundException;
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
    public UserEntity save(String username, String password) {
        // check already username exists.
        if (dao.findByUsername(username).isPresent()) {
            throw new BadRequestException("Already Exists");
        }

        // save to database
        return dao.save(new UserEntity(username, passwordEncoder.encode(password)));
    }

    @Override
    public UserEntity updateAdditionalInfo(int userId, String profileImageUrl, String introduce) {
        UserEntity user = dao.findById(userId).orElseThrow(() -> new NotFoundException("Not Found User"));
        if (profileImageUrl != null && profileImageUrl.length() != 0) {
            user.setProfile(profileImageUrl);
        }

        if (introduce != null && introduce.length() != 0) {
            user.setIntroduce(introduce);
        }

        return dao.save(user);
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
