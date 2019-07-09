/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Student;

import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.Student.Student;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author lts
 */
public interface ClassesService {

    public Classes save(Classes classes);

    /**
     *
     * @param id for class
     * @return class object
     */
    public Classes findById(Long id);

    public List<Classes> findAll();

    public List<Classes> findByStudentsStudentStateGraduated(boolean grad);

    public Classes findByName(String name);

    boolean delete(Classes classes);

    public Map<String, Integer> countGenderMaleAndFemaleAndTotal(Set<Student> arrayList);

//    public int countGenderFemale(ArrayList<Student> arrayList);
//    public File generateInvoiceFor() throws IOException;
//
//    public JasperReport loadTemplate() throws JRException;
    public List<Map<String, Object>> SchoolBudject();

    public List<Map<String, Object>> ClassesStudent(Long id);
}
