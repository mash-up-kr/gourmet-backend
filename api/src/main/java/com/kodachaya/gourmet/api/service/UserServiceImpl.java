package com.kodachaya.gourmet.api.service;

import com.kodachaya.gourmet.api.entity.UserEntity;
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
}
