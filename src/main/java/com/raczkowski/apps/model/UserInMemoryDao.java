package com.raczkowski.apps.model;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class UserInMemoryDao implements UserDao {

    private List<User> users;

    public UserInMemoryDao() {
        this.users = asList(
                new User(1, "Przemyslaw", "Raczkowski", "praczkowski@mail.com", "1234"),
                new User(1, "Tomasz", "Makowski", "maczek@mail.com", "admin")
        );
    }

    @Override
    public Optional<User> getUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findAny();
    }
}
