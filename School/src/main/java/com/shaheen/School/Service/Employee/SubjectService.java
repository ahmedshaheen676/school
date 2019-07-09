/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee;

import com.shaheen.School.Model.Employee.Subject;
import java.util.List;

/**
 *
 * @author lts
 */
public interface SubjectService {
      public List<Subject> findAll();

    public Subject save(Subject subject);

    public boolean delete(Subject subject);

    public Subject findOne(Long id);
 
}
