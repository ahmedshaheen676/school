/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee;

import com.shaheen.School.Model.Employee.Job;
import java.util.List;

/**
 *
 * @author lts
 */
public interface JobService {

    public List<Job> findAll();

    public Job save(Job job);

    public boolean delete(Job job);

    public Job findOne(Long id);

}
