package com.student.exception;

public class StudentDataAccessException extends RuntimeException {

    public StudentDataAccessException(final String message){

        super(message);
    }
    public StudentDataAccessException(final Throwable cause){

        super(cause);
    }
    public StudentDataAccessException(final String message,final Throwable cause){

        super(message,cause);
    }
}
