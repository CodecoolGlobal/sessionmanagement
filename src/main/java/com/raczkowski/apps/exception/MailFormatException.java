package com.raczkowski.apps.exception;

public class MailFormatException extends Exception {

    public MailFormatException(String mail) {
        super(String.format("Given mail (%s) has incorrect format", mail));
    }
}
