package com.academy.brawler.site;

import com.academy.brawler.authorization.users.Exceptions;
import com.academy.brawler.authorization.users.LoggedInUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private LoggedInUsers users;

    @GetMapping({"/index.html", "/index", "/"})
    public String indexPage(final Model model, final HttpServletRequest httpServletRequest) {
        List<LoggedInUsers.User> allUsers = users.getAllUsers();
        System.out.println("All users size: " + allUsers.size());
        model.addAttribute("users", allUsers);
        return "index";
    }

    @GetMapping({"/logged-in.html", "/logged-in"})
    public String logInRedirect(final Model model, final HttpServletRequest httpServletRequest) throws IOException, Exceptions.JsonToUserException {
        System.out.println("logged in");
        users.login(httpServletRequest.getSession(), httpServletRequest.getRemoteUser());
        return indexPage(model, httpServletRequest);
    }

    @GetMapping({"/logged-out.html", "/logged-out"})
    public String logOutRedirect(final Model model, final HttpServletRequest httpServletRequest) {
        System.out.println("logged out");
        users.logout(httpServletRequest.getSession());
        return indexPage(model, httpServletRequest);
    }
}
