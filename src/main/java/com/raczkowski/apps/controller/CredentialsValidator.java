package com.raczkowski.apps.controller;

import com.raczkowski.apps.exception.MailFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void validateEmail(String email) throws MailFormatException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new MailFormatException(email);
        }
    }

}
