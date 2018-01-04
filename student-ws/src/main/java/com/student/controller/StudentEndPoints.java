package com.student.controller;

import com.student.exception.ErrorResponse;
import com.student.exception.StudentServiceException;
import com.student.model.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/StudentService")
public class StudentEndPoints {


   @Autowired
   private StudentService stdService;


    @RequestMapping(value="/getStudent", method= RequestMethod.GET)
    public ResponseEntity getStudentByRollNO(@RequestParam int rollNo){
        try {
            Student studentResponse = stdService.getStudents(rollNo);
            if (null==studentResponse||isEmpty(studentResponse)){
                ErrorResponse errorResponse= new ErrorResponse("Student Details Not Found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }catch(StudentServiceException e){
            ErrorResponse errorResponse= new ErrorResponse("Student Details Unavailable");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value="/saveStudent", method= RequestMethod.POST)
    public ResponseEntity saveStudent(@RequestBody Student student){
        try {
            Student st = stdService.saveStudents(student);
            if (null==st||isEmpty(st)){
                ErrorResponse errorResponse= new ErrorResponse("Exception while saving");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(st, HttpStatus.OK);
        }catch(StudentServiceException e){
            ErrorResponse errorResponse= new ErrorResponse("Student Details Unavailable");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

        }

    }


}
