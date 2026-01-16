package com.foodapp.dao;

import com.foodapp.model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User loginUser(String email, String password);
}
