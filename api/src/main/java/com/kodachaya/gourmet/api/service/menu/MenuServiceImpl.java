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

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Autowired
    private RestaurantDao restaurantDao;


    @Override
    public boolean exists(int restaurantId, String name) {
        return menuDao.existsByRestaurantAndName(restaurantId, name);
    }

    @Override
    public MenuEntity create(int restaurantId, String name) {
        return this.create(restaurantId, name, null);
    }

    @Override
    @Transactional
    public MenuEntity create(int restaurantId, String name, Integer price) {
        if (menuDao.findByRestaurantAndName(restaurantId, name).isPresent()) {
            throw new AlreadyExistsException("Already Menu Exists");
        }

        RestaurantEntity restaurant = restaurantDao.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not Found Restaurant"));
        MenuEntity menu = new MenuEntity(name, restaurant, price);
        return menuDao.save(menu);
    }

    @Override
    @Transactional
    public MenuEntity update(int menuId, Integer price) {
        MenuEntity menu = menuDao.findById(menuId).orElseThrow(() -> new NotFoundException("Not Found Menu"));
        menu.setPrice(price);
        return menuDao.save(menu);
    }

    @Override
    public List<MenuEntity> search(int restaurantId, String name) {
        return menuDao.findAllByRestaurantAndNameContaining(restaurantId, name);
    }

    @Override
    public List<MenuEntity> findAll(int restaurantId) {
        return menuDao.findAllByRestaurant(restaurantId);
    }
}
