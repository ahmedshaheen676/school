/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee.Impl;

import com.shaheen.School.Model.Employee.Degree;
import com.shaheen.School.Repository.Employee.DegreeRepository;
import com.shaheen.School.Service.Employee.DegreeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public List<Degree> findAll() {

        return degreeRepository.findAll();
    }

    @Override
    public Degree save(Degree degree) {

        return degreeRepository.save(degree);
    }

    @Override
    public boolean delete(Degree degree) {
        Degree degreeTofind = findOne(degree.getId());
        if (degreeTofind.getDegreeName() != null && !degreeTofind.getDegreeName().equals("")) {
            degreeRepository.delete(degreeTofind);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Degree findOne(Long id) {
        return degreeRepository.getOne(id);

    }

}
