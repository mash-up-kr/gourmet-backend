package com.kodachaya.gourmet.api.service.restaurant;

import com.kodachaya.gourmet.api.dao.restaurant.RestaurantDao;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import com.kodachaya.gourmet.api.exception.AlreadyExistsException;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;


    @Override
    public boolean exists(String name) {
        return restaurantDao.existsByName(name);
    }

    @Override
    public RestaurantEntity create(String name, String address) {
        return this.create(name, address, null, null);
    }

    @Override
    @Transactional
    public RestaurantEntity create(String name, String address, Double latitude, Double longitude) {
        if (restaurantDao.findByName(name).isPresent()) {
            throw new AlreadyExistsException("Already Restaurant exists");
        }

        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        return restaurantDao.save(restaurant);
    }

    @Override
    public RestaurantEntity update(int restaurantId, String address) {
        return this.update(restaurantId, address, null, null);
    }

    @Override
    @Transactional
    public RestaurantEntity update(int restaurantId, String address, Double latitude, Double longitude) {
        RestaurantEntity restaurant = restaurantDao.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not Found Restaurant"));
        restaurant.setAddress(address);
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        return restaurantDao.save(restaurant);
    }

    @Override
    public List<RestaurantEntity> search(String name) {
        return restaurantDao.findByNameContaining(name);
    }
}
