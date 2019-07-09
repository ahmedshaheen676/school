/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Student;
import com.shaheen.School.Model.Student.StudentState;

/**
 *
 * @author lts
 */

public interface StateService {
    StudentState save(StudentState state);
    
    StudentState findeOne(long  id);
    
    boolean delete(StudentState state);
}
