/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Student;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lts
 */
@Entity
@NamedQueries(value = {
    // تخرج ؟
    @NamedQuery(name = "Student.StudentState.graduated",
            query = "SELECT  s FROM Student s WHERE s.studentState.graduated=?1 ")
    ,
    // محول من ؟
    @NamedQuery(name = "Student.StudentStateComeFrom",
            query = "SELECT s  FROM Student s WHERE s.studentState.graduated=?1 "
            + " AND s.studentState.comeFrom=?2 ")
    ,
    // محول من ؟
    @NamedQuery(name = "Student.StudentStateSendTo",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.sendTo=?2 ")
    ,
    // المتسربون؟
    @NamedQuery(name = "Student.StudentStateGetOut",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.getOut=?2 ")
    ,
    // ولي الامر مطلق
    @NamedQuery(name = "Student.StudentStateDivorsed",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.divorsed=?2 ")
    ,
    // الضمان الاجتماعي
    @NamedQuery(name = "Student.StudentStateSocialSecure",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.socialSecure=?2 ")
    ,
    // الايتام
    @NamedQuery(name = "Student.StudentStateParentDied",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.parentDied=?2 ")
    ,
    // موقوف قيده
    @NamedQuery(name = "Student.StudentStateStoped",
            query = "SELECT s  FROM Student s WHERE  s.studentState.graduated=?1  "
            + "And s.studentState.stoped=?2 ")
}
)
@Transactional
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, updatable = true, unique = true)
    @NotNull(message = "Must be a value")
    private String name;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String parentName;
    private String parentPhone;

    private Gender gender;

    @ManyToOne
    private Classes classes = new Classes();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private StudentState studentState;

    public Student() {
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the parentName
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName the parentName to set
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return the parentPhone
     */
    public String getParentPhone() {
        return parentPhone;
    }

    /**
     * @param parentPhone the parentPhone to set
     */
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    /**
     * @return the classes
     */
    public Classes getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the state
     */
    public StudentState getStudentState() {
        return studentState;
    }

    /**
     * @param studentState the state to set
     */
    public void setStudentState(StudentState studentState) {
        this.studentState = studentState;
    }


}
