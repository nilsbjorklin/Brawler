package com.academy.brawler.authorization;

import com.academy.brawler.authorization.users.CustomUser;
import com.academy.brawler.authorization.users.Exceptions;
import com.academy.brawler.authorization.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Users users;

    public boolean register(final String email, final String clearTextPassword) throws IOException, Exceptions.JsonToUserException {
        if (users.registerUser(email, clearTextPassword) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        CustomUser customUser = null;
        try {
            customUser = users.authenticateUser(authentication.getName(), authentication.getCredentials()
                    .toString());
        } catch (Exception e) {
            System.err.printf("CustomAuthenticationProvider.authenticate: Got %s with message %s%n", e.getClass().getSimpleName(), e.getLocalizedMessage());
            e.printStackTrace();
        }
        if (customUser != null) {
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(customUser.getEmail(), customUser.getPassword(), customUser.getAuthorities());
            System.out.println("Login successful: " + userToken.toString());
            return userToken;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}