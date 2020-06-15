package com.raczkowski.apps.model;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(int id,
                String name,
                String surname,
                String email,
                String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
