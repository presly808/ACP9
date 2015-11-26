package com.artcode.myproject.service;

import com.artcode.myproject.model.RentalRequirements;
import com.artcode.myproject.model.User;

public interface UserService {
    User register(String username, String password, int age, RentalRequirements requirements);
    User getUserByUsername(String username);
}
