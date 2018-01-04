package com.student.service;

import com.student.data.StudentDao;
import com.student.exception.StudentServiceException;
import com.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudents(int rollNO) throws StudentServiceException {

        return studentDao.getStudentDetailsBYRollNo(rollNO);
    }

    @Override
    public Student saveStudents(Student student)  throws StudentServiceException{

        return studentDao.saveStudentDetails(student);
    }




}
