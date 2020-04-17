package com.academy.brawler.authorization.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.academy.brawler.authorization.users.UserFakeDB.addUser;
import static com.academy.brawler.authorization.users.UserFakeDB.getUser;

@Component
public class Users {
    private static final String FILE_LOCATION = "users.txt";
    private static final File file = new File(FILE_LOCATION);

    @Autowired
    private UserFakeDB fakeDB;

    private static CustomUser createUser(final String emailString, final String cleartextPassword, final Roles.CustomRole... webpageRoles) {
        CustomUser createdUser = new CustomUser();
        createdUser.setEmail(emailString);
        createdUser.setClearTextPassword(cleartextPassword);

        for (int i = 0; i < webpageRoles.length; i++) {
            createdUser.addRole(webpageRoles[i]);
        }
        return createdUser;

    }

    public CustomUser registerUser(final String emailString, final String cleartextPassword) throws IOException, Exceptions.JsonToUserException {
        CustomUser user = getUser(emailString);
        if (user == null) {
            CustomUser newUser = createUser(emailString, cleartextPassword, Roles.CustomRole.USER);
            if (addUser(newUser)) {
                System.out.println("User registered successfully.");
                return newUser;
            }
        }
        return null;
    }

    public CustomUser authenticateUser(final String emailString, final String password) throws IOException, Exceptions.JsonToUserException {
        CustomUser user = getUser(emailString);
        if (user != null) {
            if (user.login(password)) {
                return user;
            } else {
                System.err.println("Password does not match not found");
            }
        } else {
            System.err.println("User not found");
        }

        return null;
    }
}
