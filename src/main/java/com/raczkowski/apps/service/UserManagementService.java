package com.raczkowski.apps.service;

import com.raczkowski.apps.model.User;
import com.raczkowski.apps.model.UserDao;

import java.util.Optional;

public class UserManagementService {

    private UserDao userDao;

    public UserManagementService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> login(String email, String password) {
        return userDao.getUser(email, password);
    }
}
