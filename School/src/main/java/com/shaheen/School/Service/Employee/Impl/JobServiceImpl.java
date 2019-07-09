/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee.Impl;

import com.shaheen.School.Model.Employee.Job;
import com.shaheen.School.Repository.Employee.JobRepository;
import com.shaheen.School.Service.Employee.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }

    @Override
    public Job save(Job job) {

        return jobRepository.save(job);
    }

    @Override
    public boolean delete(Job job) {
        Job jobTofind = findOne(job.getId());
        if (jobTofind.getJobTitle() != null && !jobTofind.getJobTitle().equals("")) {
            jobRepository.delete(jobTofind);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Job findOne(Long id) {
        return jobRepository.getOne(id);

    }

}
