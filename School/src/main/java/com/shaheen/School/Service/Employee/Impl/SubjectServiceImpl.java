/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee.Impl;

import com.shaheen.School.Model.Employee.Subject;
import com.shaheen.School.Repository.Employee.SubjectRepository;
import com.shaheen.School.Service.Employee.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {

        return subjectRepository.findAll();
    }

    @Override
    public Subject save(Subject subject) {

        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Subject subject) {
        Subject subjectTofind = findOne(subject.getId());
        if (subjectTofind.getSubjectTitle() != null && !subjectTofind.getSubjectTitle().equals("")) {
            subjectRepository.delete(subjectTofind);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Subject findOne(Long id) {
        return subjectRepository.getOne(id);

    }

}
