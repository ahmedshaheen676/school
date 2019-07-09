/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee;

import com.shaheen.School.Model.Employee.Degree;
import java.util.List;

/**
 *
 * @author lts
 */
public interface DegreeService {
    public List<Degree> findAll();

    public Degree save(Degree degree);

    public boolean delete(Degree degree);

    public Degree findOne(Long id);
}
