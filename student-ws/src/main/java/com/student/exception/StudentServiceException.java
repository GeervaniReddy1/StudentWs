package com.student.exception;

public class StudentServiceException extends RuntimeException {

    public StudentServiceException(final String message){

        super(message);
    }
    public StudentServiceException(final Throwable cause){

        super(cause);
    }
    public StudentServiceException(final String message,final Throwable cause){

        super(message,cause);
    }
}
