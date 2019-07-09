/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Student.Impl;

import com.shaheen.School.Model.Student.StudentState;
import com.shaheen.School.Repository.Student.StateRepository;
import com.shaheen.School.Service.Student.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class StateServiceImpl implements StateService {

    @Autowired
    StateRepository stateRepository;

    @Override
    public StudentState save(StudentState state) {
        return stateRepository.save(state);

    }

    @Override
    public StudentState findeOne(long id) {
        return stateRepository.getOne(id);

    }

    @Override
    public boolean delete(StudentState state) {
        StudentState findeOne = findeOne(state.getStateId());
        if (findeOne.getStateId()!= null) {
            stateRepository.delete(state);
            return true;
        }
        return false;
    }

}
