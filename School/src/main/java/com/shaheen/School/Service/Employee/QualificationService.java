/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee;

import com.shaheen.School.Model.Employee.Qualification;
import java.util.List;

/**
 *
 * @author lts
 */
public interface QualificationService {
        public List<Qualification> findAll();

    public Qualification save(Qualification qualification);

    public boolean delete(Qualification qualification);

    public Qualification findOne(Long id);
}
