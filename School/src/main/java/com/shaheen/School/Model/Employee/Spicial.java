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
public class Spicial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String spicialTitle;

    @OneToMany(mappedBy = "spicial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    public Spicial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the spicialTitle
     */
    public String getSpicialTitle() {
        return spicialTitle;
    }

    /**
     * @param spicialTitle the spicialTitle to set
     */
    public void setSpicialTitle(String spicialTitle) {
        this.spicialTitle = spicialTitle;
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
        return "Spicial{" + "id=" + id + ", spicialTitle=" + spicialTitle + ", employees=" + employees + '}';
    }


}
