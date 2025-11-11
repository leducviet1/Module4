package com.example.myfirststringmvc.services;

import com.example.myfirststringmvc.models.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> getStudents();
    public void save(Student student);
    public Student getStudentById(String id);
}
