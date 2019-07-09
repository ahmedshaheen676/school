/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.User;

import com.shaheen.School.Model.User.PasswordResetToken;
import com.shaheen.School.Model.User.User;
import java.util.List;
/**
 *
 * @author lts
 */
public interface UserService{

    PasswordResetToken getPasswordResetToken(final String token);

    void creatPasswordRestTokenForUser(final User user, final String token);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByUsernameAndEmail(String username, String email);

//    public void creatUser(User user, Set<UserRole> userRoles) throws Exception;

    public User save(User user);

    public User findById(Long id);

    public List<User> findAll();

    public boolean delete(Long id);
}
