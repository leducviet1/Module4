package com.example.myfirststringmvc.services;

import com.example.myfirststringmvc.models.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DBStudentService implements IStudentService {
//    private final JdbcTemplate jdbcTemplate;
//    public DBStudentService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public List<Student> getStudents() {
        return List.of();
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public Student getStudentById(String id) {
        return null;
    }
}
