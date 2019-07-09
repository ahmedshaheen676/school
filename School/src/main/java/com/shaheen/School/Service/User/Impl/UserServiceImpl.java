/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.User.Impl;

import com.shaheen.School.Model.User.User;
import com.shaheen.School.Repository.User.UserRepository;
import com.shaheen.School.Model.User.PasswordResetToken;
import com.shaheen.School.Repository.User.PasswordResetTokenRepository;
import com.shaheen.School.Repository.User.RoleRepository;
import com.shaheen.School.Service.User.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void creatPasswordRestTokenForUser(User user, String token) {
        final PasswordResetToken passwordRestToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordRestToken);
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }
//
//    @Override
//    public User creatUser(User user, Set<UserRole> userRoles) {
//        User localUser = userRepository.findByUsername(user.getUsername());
//        try {
//            if (localUser != null) {
//                return null ;
//            } else {
//                for (UserRole userRole : userRoles) {
//                    roleRepository.save(userRole.getRole());
//                }
//                user.getUserRole().addAll(userRoles);
//                localUser = userRepository.save(user);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return localUser;
//    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public boolean delete(Long id) {
        User user = findById(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByUsernameAndEmail(String username, String email) {

        return userRepository.findByUsernameAndEmail(username, email);
    }

}
