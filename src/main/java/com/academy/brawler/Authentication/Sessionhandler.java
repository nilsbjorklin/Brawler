package com.academy.brawler.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Sessionhandler {

    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user") != null) {
            User userInfo = (User) session.getAttribute("user");
            if (userInfo.getPassword() != null) {
                return true;
            }
        }
        return false;
    }

    public static void logIn(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
    }

    public static void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", null);
        session.invalidate();
    }

    public static User getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User userInfo = (User) session.getAttribute("user");
        return userInfo;
    }
}
