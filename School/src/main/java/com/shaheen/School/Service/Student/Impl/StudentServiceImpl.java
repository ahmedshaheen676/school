/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Student.Impl;

import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.Student.Gender;
import com.shaheen.School.Model.Student.Student;
import com.shaheen.School.Repository.Student.StudentRepository;
import com.shaheen.School.Service.Student.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);

    }

    @Override
    public Student findOne(long id) {
        return studentRepository.getOne(id);

    }

    @Override
    public Student findByname(String name) {
        try {
            return studentRepository.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Student> findByClass(Classes classes) {
        return studentRepository.findByClasses(classes);

    }

    @Override
    public List<Student> findByGender(Gender gender) {
        return studentRepository.findByGender(gender);

    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();

    }

    @Override
    public boolean deleteStudent(Student student) {
        Student findOne = findOne(student.getId());
        if (findOne.getId() != null) {
            studentRepository.delete(findOne);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> StudentStateGraduated(boolean graduated) {

        return studentRepository.StudentStateGraduated(graduated);
    }

    @Override
    public List<Student> StudentStateComeFrom(boolean graduated, boolean comeFrom) {

        return studentRepository.StudentStateComeFrom(graduated, comeFrom);
    }

    @Override
    public List<Student> StudentStateSendTo(boolean graduated, boolean sendTo) {

        return studentRepository.StudentStateSendTo(graduated, sendTo);
    }

    @Override
    public List<Student> StudentStateGetOut(boolean graduated, boolean getOut) {

        return studentRepository.StudentStateGetOut(graduated, getOut);

    }

    @Override
    public List<Student> StudentStateDivorsed(boolean graduated, boolean divorsed) {
        return studentRepository.StudentStateDivorsed(graduated, divorsed);
    }

    @Override
    public List<Student> StudentStateSocialSecure(boolean graduated, boolean socialSecure) {
        return studentRepository.StudentStateSocialSecure(graduated, socialSecure);

    }

    @Override
    public List<Student> StudentStateParentDied(boolean graduated, boolean parentDied) {
        return studentRepository.StudentStateParentDied(graduated, parentDied);

    }

    @Override
    public List<Student> StudentStateStoped(boolean graduated, boolean stoped) {
        return studentRepository.StudentStateParentDied(graduated, stoped);

    }

}
