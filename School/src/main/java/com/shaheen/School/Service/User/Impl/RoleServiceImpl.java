/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.User.Impl;

import com.shaheen.School.Model.User.Role;
import com.shaheen.School.Repository.User.RoleRepository;
import com.shaheen.School.Service.User.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        try {
            return roleRepository.getOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Role findByRoleName(String name) {
        if (name != null && !name.equals("")) {
            try {
                return roleRepository.findByName(name);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {

        Role findById = findById(id);
        if (findById != null) {
            roleRepository.delete(findById);
            return true;
        }

        return false;
    }

}
