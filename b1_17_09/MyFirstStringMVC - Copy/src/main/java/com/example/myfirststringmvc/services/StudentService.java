package com.example.myfirststringmvc.services;

import com.example.myfirststringmvc.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService implements IStudentService {
    public static List<Student> students = new ArrayList<>();
    public StudentService() {
        students.add(new Student("MS01","John",3.6F));
        students.add(new Student("MS02","Kay",3.2F));
    }
    public List<Student> getStudents()
    {
        return students;
    }
    public void save(Student student){
        students.add(student);
    }
    public Student getStudentById(String id)
    {
        for (Student student : students) {
            if(student.getCode().equals(id))
                return student;
        }
        return null;
    }

}
