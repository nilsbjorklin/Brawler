package com.academy.brawler.Authentication;

public class User {
    private String emailAddress;
    private String password;
    private static final String emailValidationRegex = ".*@.*..*";

    public User(final String email, final String clearText){
        this.emailAddress = validateEmail(email);
        password = encode(clearText);
    }

    public String validateEmail(String email){
        if (email.matches(emailValidationRegex)){
            return email.toLowerCase();
        } else {
            throw new  IllegalArgumentException(String.format("Invalid email '%s', must match emailValidationRegex: '%s'", email, emailValidationRegex));
        }
    }

    public boolean passwordMatches(final String clearText) {
        return password.equals(encode(clearText));
    }

    private String encode(final String clearText){
        return clearText;
    }

    public boolean emailMatches(final String email) {
        return emailAddress.equals(validateEmail(email));
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
