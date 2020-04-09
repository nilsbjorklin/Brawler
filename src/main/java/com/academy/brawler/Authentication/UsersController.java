package com.academy.brawler.Authentication;

import com.academy.brawler.Authentication.Sessionhandler;
import com.academy.brawler.Authentication.User;
import com.academy.brawler.Authentication.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;


@Controller
public class UsersController {


    @Autowired
    Users users;
  
    @GetMapping("/homeUser")
    public static ModelAndView getHomeUser(HttpServletRequest request) {
        if (Sessionhandler.isLoggedIn(request)) {
            Sessionhandler.getUserInfo(request);

            return new ModelAndView("homeUser")
                    .addObject("userinfo", Sessionhandler.getUserInfo(request));
        } else {
            String errormessage = "Du är inte inloggad";
            return new ModelAndView("redirect:/").addObject("errormessage", errormessage);
        }
    }
    public static String camelCase(String noCamel) {
        String temp = "" + noCamel.toUpperCase().charAt(0);
        temp += noCamel.substring(1).toLowerCase();
        return temp;
    }

    @GetMapping("/")
    public ModelAndView getIndex(Model model, String errormessage) {
        users.addUser("user@gmail.com", "password");
        if (errormessage == null)
            return new ModelAndView("index").addObject("isError", "hidden");
        else
            return new ModelAndView("index").addObject("isError", "alert alert-danger").addObject("errormessage", errormessage);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Sessionhandler.logOut(request);
        return "redirect:/";
    }

    @PostMapping("/login")
    public ModelAndView getLogin(@RequestParam String password, @RequestParam String email, HttpServletRequest request, Model model, String errormessage) throws NoSuchAlgorithmException, LoginException {
        User user = users.login(email, password);
        Sessionhandler.logIn(request, user);

        if (Sessionhandler.isLoggedIn(request)) {
            return new ModelAndView("redirect:homeUser");
        } else {
            if (errormessage == null)
                errormessage = "Du är inte inloggad";
            return new ModelAndView("redirect:/").addObject("errormessage", errormessage);
        }
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam String email,
                                     @RequestParam String password,
                                     HttpServletRequest request,
                                     Model model) throws NoSuchAlgorithmException {
        try{
            User user = users.addUser(email, password);
            Sessionhandler.logIn(request, user);
            generateRegistrationMessage(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Sessionhandler.isLoggedIn(request)) {
            return new ModelAndView("redirect:homeUser");
        } else {
            return new ModelAndView("redirect:/").addObject("errormessage", "Could not register user");
        }
    }

    public void generateRegistrationMessage(final User user){
        System.out.println(user.getPassword());
    }
}









