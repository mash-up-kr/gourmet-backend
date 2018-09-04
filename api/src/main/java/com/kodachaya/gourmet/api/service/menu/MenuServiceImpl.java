package com.kodachaya.gourmet.api.service.menu;

import com.kodachaya.gourmet.api.dao.restaurant.MenuDao;
import com.kodachaya.gourmet.api.dao.restaurant.RestaurantDao;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import com.kodachaya.gourmet.api.exception.AlreadyExistsException;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Autowired
    private RestaurantDao restaurantDao;


    @Override
    public boolean exists(RestaurantEntity restaurant, String name) {
        return menuDao.existsByRestaurantAndName(restaurant, name);
    }

    @Override
    public MenuEntity create(RestaurantEntity restaurant, String name) {
        return this.create(restaurant, name, null);
    }

    @Transactional
    @Override
    public MenuEntity create(RestaurantEntity restaurant, String name, Integer price) {
        if (menuDao.findByRestaurantAndName(restaurant, name).isPresent()) {
            throw new AlreadyExistsException("Already Menu Exists");
        }

        MenuEntity menu = new MenuEntity(name, restaurant, price);
        return menuDao.save(menu);
    }

    @Override
    public List<MenuEntity> search(RestaurantEntity restaurant, String name) {
        return menuDao.findAllByRestaurantAndNameContaining(restaurant, name);
    }

    @Override
    public List<MenuEntity> findAll(RestaurantEntity restaurant) {
        return menuDao.findAllByRestaurant(restaurant);
    }

    @Override
    public Optional<MenuEntity> find(RestaurantEntity restaurant, String name) {
        return menuDao.findByRestaurantAndName(restaurant, name);
    }


    @Override
    @Transactional
    public MenuEntity update(int menuId, Integer price) {
        MenuEntity menu = menuDao.findById(menuId).orElseThrow(() -> new NotFoundException("Not Found Menu"));
        menu.setPrice(price);
        return menuDao.save(menu);
    }

}
