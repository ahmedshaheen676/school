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
import com.shaheen.School.Utility.SecurityUtility;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author lts
 */
@Controller
public class UserHomeController {
//

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserSecurityService securityService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login/login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("user", user);

        return "user/profile";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String profileUpdate(HttpServletRequest request,
            @ModelAttribute @Valid User user, Model model) {
        if (user.getFirstName().equals("") || user.getFirstName().equals(null)) {
            model.addAttribute("fristNameError", true);
        }
        if (user.getLastName().equals("") || user.getLastName().equals(null)) {
            model.addAttribute("lastNameError", true);
        }
        if (user.getEmail().equals("") || user.getEmail().equals(null)) {
            model.addAttribute("emailError", true);
        }
        if (user.getEmail().equals("") || user.getEmail().equals(null)) {
            model.addAttribute("emailError", true);
        }

//        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
//        userService.save(user);
        model.addAttribute("user", user);
        return "user/profile";

//        return "redirect:/";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("user", new User());
        return "login/forgetPassword";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(HttpServletRequest request, @ModelAttribute User user, Model model,
            RedirectAttributes redirectAttributes) {
        if (user.getUsername().equals("") || user.getUsername().equals(null)) {
            model.addAttribute("usernameError", true);
            return "login/forgetPassword";

        } else {
            User findByUsername = userService.findByUsername(user.getUsername());
            if (findByUsername == null) {
                model.addAttribute("usernameNotExists", true);
                return "login/forgetPassword";
            }
        }
        if (user.getEmail().equals("") || user.getEmail().equals(null)) {
            model.addAttribute("emailError", true);
            return "login/forgetPassword";

        } else {
            User findByUsername = userService.findByEmail(user.getEmail());
            if (findByUsername == null) {
                model.addAttribute("emailNotExists", true);
                return "login/forgetPassword";
            } else {
                User findByUsernameAndEmail = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
                if (findByUsernameAndEmail != null) {

                    String password = SecurityUtility.randomPassword();

                    findByUsernameAndEmail.setPassword(SecurityUtility.passwordEncoder().encode(password));

                    userDAO.sendMail(request, findByUsernameAndEmail, password);
                    userService.save(findByUsernameAndEmail);
                    model.addAttribute("emailSend", true);
                    redirectAttributes.addFlashAttribute("message", "تم ارسال بريد يحتوي علي بيانات الدخول .");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

                    return "redirect:/login";

                } else {
                    model.addAttribute("emailSend", false);
                    redirectAttributes.addFlashAttribute("message", "  من فضلك تأكد من البيانات بشكل صحيح .");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-warning");
                    return "redirect:/forgetPassword";
                }
            }
        }

    }

    @RequestMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "login/registration";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(HttpServletRequest request,
            @ModelAttribute(name = "user", value = "user") @Valid User user, Model model) {

        // boolen to check is their eny error on values user enterd ;
        boolean error = false;

        // return with error
        Map<String, Boolean> check = userDAO.save(user);
        for (Map.Entry<String, Boolean> entry : check.entrySet()) {
            // if their any error it 
            if (entry.getValue()) {
                model.addAttribute(entry.getKey(), entry.getValue());
                error = true;
            }
//            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        model.addAttribute("user", user);
        if (error) {
//            model.addAttribute("emailSent", sendMail(request, userToSave, user.getPassword()));
            return "login/registration";
        } else {

            return "redirect:/login";
        }
    }

    @RequestMapping("/confirm")
    public String confirm(Locale locale,
            @RequestParam(name = "token") String token,
            Model model) {
        PasswordResetToken passwordToken = userService.getPasswordResetToken(token);

        Date expiryDate;
        if (passwordToken == null) {
            String message = "Invalid Token .";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        } else {
            System.out.println("exist and compare");
            expiryDate = passwordToken.getExpiryDate();

            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            Date now = new Date(calendar.getTime().getTime());
//            int compareTo = now.compareTo(expiryDate);
            if (now.getTime() < expiryDate.getTime()) {
                System.out.println("enter the compare and valid");
                User user = passwordToken.getUser();
                String username = user.getUsername();

                UserDetails userDetails = securityService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                        userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                model.addAttribute("user", user);
                model.addAttribute("classActiveEdit", true);

                return "user/profile";
            } else {
                System.out.println("enter the compare and not valid ");
                String message = "Invalid Token .";
                model.addAttribute("message", message);
                return "redirect:/badRequest";
            }
        }

    }

}
