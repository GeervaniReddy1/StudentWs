package com.student.service;

import com.student.exception.StudentServiceException;
import com.student.model.Student;


public interface StudentService {

    Student getStudents(int roll) throws StudentServiceException;
    Student saveStudents(Student student) throws StudentServiceException;
}
