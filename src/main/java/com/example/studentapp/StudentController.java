package com.example.studentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {



    @Autowired
    StudentRepo studentRepo;
    @RequestMapping("/getStudents")
    public List<Student> getStudents(){
       return studentRepo.findAll();
    }

    @RequestMapping("/add/Student")
    public void addStudent(){
        Student s=new Student();
        s.setName("Arahuk");
        s.setAge(65);
        studentRepo.save(s);

    }
}
