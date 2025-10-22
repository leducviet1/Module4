package com.example.myfirststringmvc.services;

import com.example.myfirststringmvc.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//bỏ bean này ra khỏi ioc ( không phải tạo khi spring khởi động)
public class StudentService implements IStudentService {
    public static List<Student> students = new ArrayList<>();
    public StudentService() {
        students.add(new Student("MS01","John",3.6F));
        students.add(new Student("MS02","Kay",3.2F));
    }
    public List<Student> findAll(String q, String sort, String dir){
        List<Student> filteredStudent = new ArrayList<>();
        for(Student student : students){
            if(q==null || q.isEmpty()
                    ||student.getCode().toLowerCase().contains(q.toLowerCase())
                    ||student.getName().toLowerCase().contains(q.toLowerCase())
                    ){
            filteredStudent.add( student);
            }
        }
       return filteredStudent;
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

    public boolean existsById(String id) {
        for (Student student : students) {
            if(student.getCode().equals(id)){
                return true;
            }
        }
        return false;
    }
    public void update(String code,Student student) {
        Optional<Student> optional = Optional.ofNullable(getStudentById(code));
        if(optional.isEmpty()) throw new IllegalArgumentException("Không tìm thấy sinh viên ");
        Student found =  optional.get();
        if(!code.equals(found.getCode()) && existsById(found.getCode()) ){
            throw new IllegalArgumentException("Mã mới đã bị trùng");
        }
        found.setCode(student.getCode());
        found.setName(student.getName());
        found.setGpa(student.getGpa());
    }
    public void deleteById(String id) {
        students.removeIf(student -> student.getCode().equals(id));
    }

}
