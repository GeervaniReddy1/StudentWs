package com.student.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication //To specify it is a spring boot application
@ComponentScan(basePackages = {"com.student"})// to specify the components
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args); // call a method run, to run this application
    }
}
