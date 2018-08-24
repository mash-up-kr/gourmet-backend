package com.kodachaya.gourmet.api.service.wish;

import com.kodachaya.gourmet.api.dao.user.UserDao;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WishServiceImpl implements WishService {


    @Override
    public List<WishEntity> getWishes(int userId, int cursor, int count) {
        return null;
    }

    @Override
    public boolean deleteWish(int userId, int wishId) {
        return false;
    }

    @Override
    public Optional<WishEntity> bookmarkWish(int userId, int wishId) {
        return Optional.empty();
    }

    @Override
    public int getWishCount() {
        return 0;
    }
}
