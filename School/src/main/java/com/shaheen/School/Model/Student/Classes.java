/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lts
 */
@Entity
@NamedQueries(value = {
    @NamedQuery(name = "Classes.xyz",
            query = "SELECT  c FROM Classes c WHERE c.students in"
            + " (SELECT  s FROM Student s WHERE s.studentState.graduated=?1)")
//        select p from Person p where p.address in (select a from Address a where a.houseNumber > :houseNumber)');

})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "parentActivity"})
@Transactional
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    public Classes() {
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Students
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * @param Students the Students to set
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", name=" + name + ", Students=" + students + '}';
    }

}
