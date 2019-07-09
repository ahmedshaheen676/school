/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Student.Impl;

import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.Student.Gender;
import com.shaheen.School.Model.Student.Student;
import com.shaheen.School.Model.Student.StudentState;
import com.shaheen.School.Repository.Student.ClassesRepository;
import com.shaheen.School.Service.Student.ClassesService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public Classes save(Classes classes) {
        return classesRepository.save(classes);

    }

    @Override
    public Classes findById(Long id) {
        try {
            Classes classes = classesRepository.getOne(id);
            if (classes != null) {
                return classes;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Classes> findAll() {
        return getAllNotGraduatedStudents(classesRepository.findAll());

    }

    @Override
    public Classes findByName(String name) {
        try {
            return classesRepository.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Classes classes) {
        try {
            
            if (classes != null) {
                classesRepository.delete(classes);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Map<String, Integer> countGenderMaleAndFemaleAndTotal(Set<Student> arrayList) {
        Map<String, Integer> countOfGenderAndTotal = new HashMap<>();

        int countMale = 0;
        int countFmale = 0;

        for (Student student : arrayList) {
            if (student.getGender() == Gender.MALE) {
                countMale = countMale + 1;
            } else if (student.getGender() == Gender.FEMALE) {
                countFmale = countFmale + 1;
            }
        }
        countOfGenderAndTotal.put("countMale", countMale);
        countOfGenderAndTotal.put("countFmale", countFmale);
        countOfGenderAndTotal.put("Total", countMale + countFmale);

        return countOfGenderAndTotal;
    }

    @Override
    public List<Classes> findByStudentsStudentStateGraduated(boolean grad) {
        return classesRepository.xyz(grad);
    }

    @Override
    public List<Map<String, Object>> SchoolBudject() {

        List<Map<String, Object>> result = new ArrayList<>();
        for (Classes classes : findAll()) {
            Map<String, Object> map = new HashMap<>();
            Map<String, Integer> count = countGenderMaleAndFemaleAndTotal(classes.getStudents());
            map.put("className", classes.getName());
            map.put("maleCount", count.get("countMale"));
            map.put("fmaleCount", count.get("countFmale"));
            map.put("total", count.get("Total"));
            result.add(map);
        }

        return result;
    }

    private ArrayList<Classes> getAllNotGraduatedStudents(List<Classes> allClasses) {
        ArrayList<Classes> emptyClasslist = new ArrayList<Classes>();

        for (Classes classes : allClasses) {

            Set<Student> students = classes.getStudents();

            Set<Student> emptyStudentList = new HashSet<Student>();

            for (Student student : students) {
                StudentState studentState = student.getStudentState();
                if (!studentState.isGraduated()) {
                    emptyStudentList.add(student);
                }
                classes.setStudents(emptyStudentList);

            }
            emptyClasslist.add(classes);
        }
//        classesService.countGenderFemale(arrayList)
        return emptyClasslist;
    }

    @Override
    public List<Map<String, Object>> ClassesStudent(Long id) {
        List<Map<String, Object>> result = new ArrayList<>();

        Classes classes = findById(id);
        for (Student student : classes.getStudents()) {
            Map<String, Object> map = new HashMap<>();
            if (student.getStudentState().isGraduated() == false) {

                map.put("className", classes.getName());

                if (student.getGender() == Gender.MALE) {
                    System.out.println("maleStudentName " + student.getName());
                    System.out.println("maleStudentState " + student.getStudentState().isNewYear());
                    map.put("maleStudentName", student.getName());
                    map.put("malestudentState", "مستجد");

                } else if (student.getGender() == Gender.FEMALE) {
                    System.out.println("fmaleStudentName " + student.getName());
                    System.out.println("maleStudentState " + student.getStudentState().isNewYear());

                    map.put("fmaleStudentName", student.getName());
//                    map.put("maleStudentState", student.getStudentState().isNewYear());
                    map.put("fmalestudentState", "مستجد");
                }

                result.add(map);
            }
        }

        return result;

    }

}
