/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller;

import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.User.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lts
 */
@Controller
@RequestMapping("/Admin")
public class AdminHomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", ""})
    public String index(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") && !request.isUserInRole("")) {
            return "redirect:/Admin/home";
        } else {
            return "/login";
        }
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request, Model model, Principal principal) {
        if (request.isUserInRole("ROLE_ADMIN") && !request.isUserInRole("")) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            return "home";
        } else {
            return "/login";
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/myProfile")
    public String myProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

}
