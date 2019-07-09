/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Employee;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lts
 */
@Entity
@Transactional
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // الاسم
    private String employeeName;

    // الوظيفة
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Job job = new Job();

//    // الدرجة الحالية//   
    @ManyToMany
    private Set<Degree> degrees = new HashSet<>();
    //id الدرجه الحالية
    private long currentDegreeId;
    //تاريخها
    @Temporal(TemporalType.DATE)
    private Date dateOfWorkingCurrentDegree;

    //تاريخ السابقة
    @Temporal(TemporalType.DATE)
    private Date dateOfWorkingExDegree;

    //المؤهل الدراسي
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Qualification qualification = new Qualification();
    //تاريخ المؤهل الدراسي
    @Temporal(TemporalType.DATE)
    private Date qualificationDate;
    // التخصص
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Spicial spicial;

    //تاريخ الاشتغال
    @Temporal(TemporalType.DATE)
    private Date dateOfWorking;

    //تاريخ استلام العمل
    @Temporal(TemporalType.DATE)
    private Date dateOfAttending;

    //تاريخ الميلاد
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    // الرقم القومي
    private String nationalID;

    //رقم الهاتف
    private String phone;
    // العنوان
    private String employeeAddress;

//    //المسند اليه
    @ManyToMany
    private Set<Subject> subjects = new HashSet<>();
    
 
    

    public Employee() {
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
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @return the dateOfWorkingCurrentDegree
     */
    public Date getDateOfWorkingCurrentDegree() {
        return dateOfWorkingCurrentDegree;
    }

    /**
     * @param dateOfWorkingCurrentDegree the dateOfWorkingCurrentDegree to set
     */
    public void setDateOfWorkingCurrentDegree(Date dateOfWorkingCurrentDegree) {
        this.dateOfWorkingCurrentDegree = dateOfWorkingCurrentDegree;
    }

    /**
     * @return the dateOfWorkingExDegree
     */
    public Date getDateOfWorkingExDegree() {
        return dateOfWorkingExDegree;
    }

    /**
     * @param dateOfWorkingExDegree the dateOfWorkingExDegree to set
     */
    public void setDateOfWorkingExDegree(Date dateOfWorkingExDegree) {
        this.dateOfWorkingExDegree = dateOfWorkingExDegree;
    }


    /**
     * @return the dateOfWorking
     */
    public Date getDateOfWorking() {
        return dateOfWorking;
    }

    /**
     * @param dateOfWorking the dateOfWorking to set
     */
    public void setDateOfWorking(Date dateOfWorking) {
        this.dateOfWorking = dateOfWorking;
    }

    /**
     * @return the dateOfAttending
     */
    public Date getDateOfAttending() {
        return dateOfAttending;
    }

    /**
     * @param dateOfAttending the dateOfAttending to set
     */
    public void setDateOfAttending(Date dateOfAttending) {
        this.dateOfAttending = dateOfAttending;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the nationalID
     */
    public String getNationalID() {
        return nationalID;
    }

    /**
     * @param nationalID the nationalID to set
     */
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the currentDegreeId
     */
    public long getCurrentDegreeId() {
        return currentDegreeId;
    }

    /**
     * @param currentDegreeId the currentDegreeId to set
     */
    public void setCurrentDegreeId(long currentDegreeId) {
        this.currentDegreeId = currentDegreeId;
    }

    /**
     * @return the degrees
     */
    public Set<Degree> getDegrees() {
        return degrees;
    }

    /**
     * @param degrees the degrees to set
     */
    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    /**
     * @return the subjects
     */
    public Set<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * @return the employeeAddress
     */
    public String getEmployeeAddress() {
        return employeeAddress;
    }

    /**
     * @param employeeAddress the employeeAddress to set
     */
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    /**
     * @return the spicial
     */
    public Spicial getSpicial() {
        return spicial;
    }

    /**
     * @param spicial the spicial to set
     */
    public void setSpicial(Spicial spicial) {
        this.spicial = spicial;
    }

    /**
     * @return the qualificationDate
     */
    public Date getQualificationDate() {
        return qualificationDate;
    }

    /**
     * @param qualificationDate the qualificationDate to set
     */
    public void setQualificationDate(Date qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", employeeName=" + employeeName + ", job=" + job + ", degrees=" + degrees + ", currentDegreeId=" + currentDegreeId + ", dateOfWorkingCurrentDegree=" + dateOfWorkingCurrentDegree + ", dateOfWorkingExDegree=" + dateOfWorkingExDegree + ", qualifcation=" + qualification + ", qualificationDate=" + qualificationDate + ", spicial=" + spicial + ", dateOfWorking=" + dateOfWorking + ", dateOfAttending=" + dateOfAttending + ", dateOfBirth=" + dateOfBirth + ", nationalID=" + nationalID + ", phone=" + phone + ", employeeAddress=" + employeeAddress + ", subjects=" + subjects + '}';
    }

    /**
     * @return the qualification
     */
    public Qualification getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    
}
