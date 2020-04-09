package com.academy.brawler.Authentication;

import org.springframework.stereotype.Component;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Users {
    List<User> userList;
    Users(){
        userList = new ArrayList<>();
    }

    public User login(final String email, final String cleartextPassword) throws LoginException {
        User user = findUser(email);
        if (user == null || !user.passwordMatches(cleartextPassword)){
            throw new LoginException("Invalid credentials");
        } else {
            return user;
        }
    }

    public void printUsers(){
        System.err.printf("Printing %d users.%n%s%n", userList.size(), userList.stream().map(User::getEmailAddress).collect(Collectors.joining(",")));
    }

    public User addUser(final String email, final String cleartextPassword) throws KeyAlreadyExistsException{
        if (userExists(email)){
            printUsers();
            //throw new KeyAlreadyExistsException(String.format("The email '%s', already exists as user.", email));
            System.err.println(String.format("The email '%s', already exists as user.", email));
            return null;
        } else {
            User user = new User(email, cleartextPassword);
           userList.add(user);
           return user;
        }
    }

    private boolean userExists(final String email){
        return findUser(email) != null;
    }

    private User findUser(final String email){
        for (User user : userList) {
            if (user.emailMatches(email)){
                return user;
            }
        }
        return null;
    }
}
