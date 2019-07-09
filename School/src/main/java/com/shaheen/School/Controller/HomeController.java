/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller;

import com.shaheen.School.DAO.User.UserDAO;
import com.shaheen.School.Model.User.PasswordResetToken;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.User.Impl.UserSecurityService;
import com.shaheen.School.Service.User.UserService;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lts
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = {"/", "", "home", "index"})
    public String index(Model model, Principal principal) {
        if (principal != null) {
            System.out.println("principal.getName() = " + principal.getName());
            User user = userService.findByUsername(principal.getName());
            if (user.getId() > 0 && user.getId() != null) {
                model.addAttribute("user", user);
            }
        }
        return "home";
    }

    @RequestMapping("/NotFoundPage")
    public String notFoundPage() {
        return "common/NotFoundPage";
    }

    @RequestMapping("/BadRequestPage")
    public String badRequestPage() {
        return "common/BadRequestPage";
    }

   

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request, Model model, Principal principal) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/Admin/home";
        } else if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/";
        } else {
            return "redirect:/BadRequestPage";
        }
    }

}
