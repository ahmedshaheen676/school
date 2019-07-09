/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Repository.Student;

import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.Student.Gender;
import com.shaheen.School.Model.Student.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lts
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByName(String name);

    public List<Student> findByClasses(Classes classes);

    public List<Student> findByGender(Gender gender);
//    public List<Student> finedNotGraduatedStudent(boolean b);

    public List<Student> StudentStateGraduated(boolean graduated);

    public List<Student> StudentStateComeFrom(boolean graduated, boolean comeFrom);

    public List<Student> StudentStateSendTo(boolean graduated, boolean sendTo);

    public List<Student> StudentStateGetOut(boolean graduated, boolean getOut);

    public List<Student> StudentStateDivorsed(boolean graduated, boolean divorsed);

    public List<Student> StudentStateSocialSecure(boolean graduated, boolean socialSecure);

    public List<Student> StudentStateParentDied(boolean graduated, boolean parentDied);

    public List<Student> StudentStateStoped(boolean graduated, boolean stoped);

}
