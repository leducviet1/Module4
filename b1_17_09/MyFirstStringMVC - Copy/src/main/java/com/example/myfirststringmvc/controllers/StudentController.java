package com.example.myfirststringmvc.controllers;

import com.example.myfirststringmvc.models.Student;
import com.example.myfirststringmvc.services.IStudentService;
import com.example.myfirststringmvc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    IStudentService studentService;
    @GetMapping("/students")   //danh sách sinh viên
    public ModelAndView getStudents() {
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("students", studentService.getStudents());
        return modelAndView;
    }
    @GetMapping("/students/{id}")
    public ModelAndView getStudent(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("detail-student");
        modelAndView.addObject("student", studentService.getStudentById(id));
        return modelAndView;
    }
    @GetMapping("/students/create") //trả về form thêm sinh viên
    public String getStudentCreateForm(){
        return "student-form";
    }
    @PostMapping("/students/create")
    public String createStudent(@ModelAttribute Student student){
        studentService.save(student);
        return "redirect:/students";
    }

}
