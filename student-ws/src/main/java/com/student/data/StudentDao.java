package com.student.data;

import com.student.model.Student;

public interface StudentDao {

    Student getStudentDetailsBYRollNo(int rollNO);
    Student saveStudentDetails(Student student);

}
