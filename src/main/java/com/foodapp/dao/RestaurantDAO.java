package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Restaurant;

public interface RestaurantDAO {

    void addRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();
}
