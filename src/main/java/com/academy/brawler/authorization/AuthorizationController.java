package com.academy.brawler.authorization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizationController {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @PostMapping("/register.html")
    public String registerUserView(@RequestParam final String email, @RequestParam final String password, final Model model, final HttpServletRequest httpServletRequest)
            throws ServletException {
        if (httpServletRequest.getRemoteUser() != null) {
            return "redirect:index.html";
        } else {
            boolean registered = false;
            try {
                registered = authenticationProvider.register(email, password);
            } catch (Exception e) {
                System.err.printf("AuthorizationController.registerUserView: Got %s with message %s%n", e.getClass().getSimpleName(), e.getLocalizedMessage());
                e.printStackTrace();
            }
            if (registered) {
                httpServletRequest.login(email, password);
                return "redirect:/logged-in.html";
            } else {
                return registerError(model);
            }
        }
    }

    @GetMapping({"/register.html", "/register"})
    public String register() {
        return "register.html";
    }

    @GetMapping("/register-error.html")
    public String registerError(final Model model) {
        model.addAttribute("registerError", true);
        return "register.html";
    }

    @GetMapping({"/login.html", "/login"})
    public String login(final HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getRemoteUser() != null) {
            return "redirect:/logged-in.html";
        } else {
            return "login.html";
        }
    }

    @GetMapping("/login-error.html")
    public String loginError(final Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}
