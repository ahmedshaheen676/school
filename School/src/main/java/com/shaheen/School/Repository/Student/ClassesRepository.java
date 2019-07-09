/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Repository.Student;

import com.shaheen.School.Model.Student.Classes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lts
 */
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    public Classes findByName(String name);

    public List<Classes> findByStudentsStudentStateGraduated(boolean grad);

    public List<Classes> xyz(boolean grad);
    
//    public List<Student> StudentStateGraduated(boolean graduated);
}
