package com.student.model;

import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
//import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class Student implements Serializable{

    private String firstName;
    private String lastName;
    private int rollNo;
    private String section;
    private String course;
    private  long phoneNo;
    private String collegeName;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
   /* @Override
    public String encoding() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
*/}
