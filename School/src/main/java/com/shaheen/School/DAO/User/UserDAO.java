/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.DAO.User;

import com.shaheen.School.Model.User.Role;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.User.Impl.UserSecurityService;
import com.shaheen.School.Service.User.RoleService;
import com.shaheen.School.Service.User.UserService;
import com.shaheen.School.Utility.MailConstructor;
import com.shaheen.School.Utility.SecurityUtility;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lts
 */
@Repository
public class UserDAO {

    @Autowired
    MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    UserSecurityService securityService;

    @Autowired
    RoleService roleService;

    public boolean sendMail(HttpServletRequest request, User savedUser, String password) {
        String token = UUID.randomUUID().toString();
        userService.creatPasswordRestTokenForUser(savedUser, token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
        SimpleMailMessage email = mailConstructor.
                constructResetTokenEmail(appUrl, Locale.ENGLISH, token, savedUser, password);
//        email.
        System.err.println(email);
        return true;
    }

    public Map<String, Boolean> checkValidation(User user) {
        Map<String, Boolean> check = new HashMap<>();
        if (user.getFirstName() == null || user.getFirstName() == "") {
            check.put("firstNameError", true);
        } else {
            check.put("firstNameError", false);
        }
        if (user.getLastName() == null || user.getLastName() == "") {
            check.put("lastNameError", true);
        } else {
            check.put("lastNameError", false);
        }
        if (user.getPhone() == null || user.getPhone() == "") {
            check.put("phoneError", true);
        } else {
            check.put("phoneError", false);
        }
        if (user.getUsername() == null || user.getUsername() == "") {
            check.put("usernameError", true);
        } else {
            check.put("usernameError", false);
        }
        if (user.getEmail() == null || user.getEmail() == "") {
            check.put("emailError", true);
        } else {
            check.put("emailError", false);
        }
        if (user.getPassword() == null || user.getPassword() == "") {
            check.put("passwordError", true);
        } else {
            check.put("passwordError", false);
        }
        return check;
    }

    public Map<String, Boolean> save(User user) {

        Map<String, Boolean> check = checkValidation(user);
        // loop on validion of user enterd

        if (userService.findByUsername(user.getUsername()) != null) {
            check.put("usernameExists", true);
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            check.put("emailExists", true);
        }
        try {
            System.out.println(user.toString());

            //user details
            User userToSave = new User();

            userToSave.setFirstName(user.getFirstName());
            userToSave.setLastName(user.getLastName());
            userToSave.setPhone(user.getPhone());

            userToSave.setUsername(user.getUsername());
            userToSave.setEmail(user.getEmail());
//        String password = SecurityUtility.randomPassword();
            String password = user.getPassword();
            String encryptPassword = SecurityUtility.passwordEncoder().encode(password);

            userToSave.setPassword(encryptPassword);

            //user role
            Role role = new Role();
            role.setId(1l);
            role.setName("ROLE_USER");
//            role.getUsers().add(userToSave);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
//            
//            roleService.save(role);
            roleService.save(role);
            
            userToSave.setRoles(roles);
//            user.getRoles().add(role);

            userService.save(userToSave);
            check.put("userToSave", false);

        } catch (Exception e) {
            e.printStackTrace();
            check.put("userToSave", true);

        }

        return check;

    }
}
