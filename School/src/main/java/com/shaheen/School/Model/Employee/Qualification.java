/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Employee;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

/**
 *
 * @author lts
 */
@Entity
@Transactional
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String qualificationTitle;

    @OneToMany(mappedBy = "qualification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    public Qualification() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the qualificationTitle
     */
    public String getQualificationTitle() {
        return qualificationTitle;
    }

    /**
     * @param qualificationTitle the qualificationTitle to set
     */
    public void setQualificationTitle(String qualificationTitle) {
        this.qualificationTitle = qualificationTitle;
    }

    /**
     * @return the employees
     */
    public Set<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Qualification{" + "id=" + id + ", qualificationTitle=" + qualificationTitle + ", employees=" + employees + '}';
    }

}
