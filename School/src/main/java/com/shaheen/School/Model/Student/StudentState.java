/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Student;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lts
 */
@Entity
@Transactional
public class StudentState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stateId;

    @OneToOne(mappedBy = "studentState", cascade = CascadeType.ALL)
    private Student student ;

    // مستجد
    private boolean newYear = true;

    //محول من
    private boolean comeFrom = false;
    // من مدرسة  ؟
    private String comeFromSchool;

    //محول الي
    private boolean sendTo = false;
    // الي مدرسة  ؟
    private String sendToSchool;

    // متسرب
    private boolean getOut = false;
    //سبب التسرب
    private String getOutCause;
    // علاج التسرب
    private String getOutCure;

    // ولي الامر مطلق
    private boolean divorsed = false;
    // المعيل
    private String whoPaid;

    // ضمان اجتماعي
    private boolean socialSecure = false;

    // يتيم
    private boolean parentDied = false;

    // موقوف قيده
    private boolean stoped = false;

    // تخرج
    private boolean graduated = false;

    public StudentState() {
    }

    /**
     * @return the id
     */
    public Long getStateId() {
        return stateId;
    }

    /**
     * @param id the id to set
     */
    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the newYear
     */
    public boolean isNewYear() {
        return newYear;
    }

    /**
     * @param newYear the newYear to set
     */
    public void setNewYear(boolean newYear) {
        this.newYear = newYear;
    }

    /**
     * @return the comeFrom
     */
    public boolean isComeFrom() {
        return comeFrom;
    }

    /**
     * @param comeFrom the comeFrom to set
     */
    public void setComeFrom(boolean comeFrom) {
        this.comeFrom = comeFrom;
    }

    /**
     * @return the comeFromSchool
     */
    public String getComeFromSchool() {
        return comeFromSchool;
    }

    /**
     * @param comeFromSchool the comeFromSchool to set
     */
    public void setComeFromSchool(String comeFromSchool) {
        this.comeFromSchool = comeFromSchool;
    }

    /**
     * @return the sendTo
     */
    public boolean isSendTo() {
        return sendTo;
    }

    /**
     * @param sendTo the sendTo to set
     */
    public void setSendTo(boolean sendTo) {
        this.sendTo = sendTo;
    }

    /**
     * @return the sendToSchool
     */
    public String getSendToSchool() {
        return sendToSchool;
    }

    /**
     * @param sendToSchool the sendToSchool to set
     */
    public void setSendToSchool(String sendToSchool) {
        this.sendToSchool = sendToSchool;
    }

    /**
     * @return the getOut
     */
    public boolean isGetOut() {
        return getOut;
    }

    /**
     * @param getOut the getOut to set
     */
    public void setGetOut(boolean getOut) {
        this.getOut = getOut;
    }

    /**
     * @return the getOutCause
     */
    public String getGetOutCause() {
        return getOutCause;
    }

    /**
     * @param getOutCause the getOutCause to set
     */
    public void setGetOutCause(String getOutCause) {
        this.getOutCause = getOutCause;
    }

    /**
     * @return the getOutCure
     */
    public String getGetOutCure() {
        return getOutCure;
    }

    /**
     * @param getOutCure the getOutCure to set
     */
    public void setGetOutCure(String getOutCure) {
        this.getOutCure = getOutCure;
    }

    /**
     * @return the divorsed
     */
    public boolean isDivorsed() {
        return divorsed;
    }

    /**
     * @param divorsed the divorsed to set
     */
    public void setDivorsed(boolean divorsed) {
        this.divorsed = divorsed;
    }

    /**
     * @return the whoPaid
     */
    public String getWhoPaid() {
        return whoPaid;
    }

    /**
     * @param whoPaid the whoPaid to set
     */
    public void setWhoPaid(String whoPaid) {
        this.whoPaid = whoPaid;
    }

    /**
     * @return the socialSecure
     */
    public boolean isSocialSecure() {
        return socialSecure;
    }

    /**
     * @param socialSecure the socialSecure to set
     */
    public void setSocialSecure(boolean socialSecure) {
        this.socialSecure = socialSecure;
    }

    /**
     * @return the stoped
     */
    public boolean isStoped() {
        return stoped;
    }

    /**
     * @param stoped the stoped to set
     */
    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    /**
     * @return the parentDied
     */
    public boolean isParentDied() {
        return parentDied;
    }

    /**
     * @param parentDied the parentDied to set
     */
    public void setParentDied(boolean parentDied) {
        this.parentDied = parentDied;
    }

    @Override
    public String toString() {
        return "studentState{" + "id=" + getStateId() + ", student=" + getStudent() + ", newYear=" + isNewYear() + ", comeFrom=" + isComeFrom() + ", comeFromSchool=" + getComeFromSchool() + ", sendTo=" + isSendTo() + ", sendToSchool=" + getSendToSchool() + ", getOut=" + isGetOut() + ", getOutCause=" + getGetOutCause() + ", getOutCure=" + getGetOutCure() + ", divorsed=" + isDivorsed() + ", whoPaid=" + getWhoPaid() + ", socialSecure=" + isSocialSecure() + ", parentDied=" + isParentDied() + ", stoped=" + isStoped() + ", garuated=" + isGraduated() + '}';
    }

    /**
     * @return the graduated
     */
    public boolean isGraduated() {
        return graduated;
    }

    /**
     * @param graduated the graduated to set
     */
    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

}
