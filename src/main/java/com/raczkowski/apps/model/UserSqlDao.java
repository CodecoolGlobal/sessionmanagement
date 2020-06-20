package com.raczkowski.apps.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class UserSqlDao implements UserDao {
    private List<User> users;

    public UserSqlDao() {
        this.users = asList(
                new User(1, "Przemyslaw", "Raczkowski", "praczkowski@mail.com", "1234"),
                new User(1, "Tomasz", "Makowski", "maczek@mail.com", "admin")
        );
    }
    
    @Override
    public Optional<User> getUser(String email, String password) {
        try {
            User userFromDb = getUserFromDb();
            return Optional.of(userFromDb);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserFromDb() throws SQLException {
        throw new SQLException();
    }
}
