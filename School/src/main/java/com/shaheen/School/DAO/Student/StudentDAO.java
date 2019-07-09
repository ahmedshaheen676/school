/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.DAO.Student;

import com.shaheen.School.Model.Student.Gender;
import com.shaheen.School.Model.Student.Student;
import com.shaheen.School.Service.Student.StudentService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lts
 */
@Repository
public class StudentDAO {

    @Autowired
    private StudentService studentService;

    public Map<String, Boolean> checkValidation(Student student) {
        Map<String, Boolean> errors = new HashMap<>();
        if (student.getName() == null || student.getName().trim().equals("")) {
            errors.put("nameError", true);
        } else {
            errors.put("nameError", false);
        }
        if (student.getAddress() == null || student.getAddress().trim().equals("")) {
            errors.put("addressError", true);

        } else {
            errors.put("addressError", false);
        }
        if (student.getParentName() == null || student.getParentName().trim().equals("")) {
            errors.put("parentNameError", true);

        } else {
            errors.put("parentNameError", false);
        }
        if (student.getParentPhone() == null || student.getParentPhone().trim().equals("")) {
            errors.put("parentPhoneError", true);

        } else {
            errors.put("parentPhoneError", false);
        }
        if (student.getDob() == null || student.getDob().after(new Date())) {
            errors.put("dobError", true);

        } else {
            errors.put("dobError", false);
        }
        if (!student.getGender().equals(Gender.MALE) ||
                !student.getGender().equals(Gender.FEMALE)
               ) {
            System.out.println(student.getGender());
            System.out.println(Gender.MALE);
            System.out.println(Gender.FEMALE);
            errors.put("genderError", true);

        } else {
            errors.put("genderError", false);
        }

        if (student.getClasses() == null || student.getClasses().getId() <= 0) {
            errors.put("classesError", true);

        } else {
            errors.put("classesError", false);
        }

        return errors;
    }

    public Map<String, Boolean> save(Student student) {
        Map<String, Boolean> checkValidation = checkValidation(student);

        System.out.println("student.getName() =" + student.getName());
        Student em = studentService.findByname(student.getName());

        if (em != null) {
            System.out.println("iiiiiiiiii" + em.getId());
            System.out.println("nameExists ==== true");
            checkValidation.put("nameExists", true);

            return checkValidation;
        } else {

            try {
                studentService.save(student);
                checkValidation.put("studentSavedError", false);

            } catch (Exception e) {
                e.printStackTrace();
                checkValidation.put("studentSavedError", true);

            }
        }
        return checkValidation;
    }
}
