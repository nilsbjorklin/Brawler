package com.academy.brawler.authorization.users;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Component
public class LoggedInUsers {
    private TreeMap<String, User> users;

    public LoggedInUsers() {
        this.users = new TreeMap<>();
    }

    public void logout(final HttpSession session) {
        users.remove(session.getId());
    }

    public void login(final HttpSession sessionObject, final String email) throws IOException, Exceptions.JsonToUserException {
        users.put(sessionObject.getId(), new User(sessionObject, email));
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        users.forEach((k, v) -> userList.add(v));
        return userList;
    }

    public static class User {
        private CustomUser user;
        private HttpSession session;

        public User(final HttpSession sessionObject, final String emailAddress) throws IOException, Exceptions.JsonToUserException {
            this.user = UserFakeDB.getUser(emailAddress);
            this.session = sessionObject;
        }

        public String getName() {
            return getEmail();
        }

        public String getEmail() {
            return user.getEmail();
        }

        public String getPassword() {
            return user.getPassword();
        }

        public String getSessionId() {
            return session.getId();
    }
    }
}
