package com.raczkowski.apps.model;

import java.util.Optional;

public interface UserDao {

    Optional<User> getUser(String email, String password);

}
