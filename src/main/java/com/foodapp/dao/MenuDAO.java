package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.MenuItem;

public interface MenuDAO {
    List<MenuItem> getMenuByRestaurant(int restaurantId);
    MenuItem getMenuById(int menuId);
}
