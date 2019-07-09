/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee.Impl;

import com.shaheen.School.Model.Employee.Qualification;
import com.shaheen.School.Repository.Employee.QualificationRepository;
import com.shaheen.School.Service.Employee.QualificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    QualificationRepository qualificationRepository;

    @Override
    public List<Qualification> findAll() {

        return qualificationRepository.findAll();
    }

    @Override
    public Qualification save(Qualification qualification) {

        return qualificationRepository.save(qualification);
    }

    @Override
    public boolean delete(Qualification qualification) {
        Qualification qualificationTofind = findOne(qualification.getId());
        if (qualificationTofind.getQualificationTitle() != null && !qualificationTofind.getQualificationTitle().equals("")) {
            qualificationRepository.delete(qualificationTofind);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Qualification findOne(Long id) {
        return qualificationRepository.getOne(id);

    }

}
