/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.User;

import com.shaheen.School.Model.User.Role;

/**
 *
 * @author lts
 */

public interface RoleService {

    public Role findById(Long id);

    public Role findByRoleName(String name);

    public Role save(Role role);

    public boolean delete(Long id);

}
