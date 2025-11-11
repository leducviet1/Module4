package com.example.myfirststringmvc.controllers;

import com.example.myfirststringmvc.models.Student;
import com.example.myfirststringmvc.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    IStudentService studentService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("today", LocalDate.now());
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "home";   //tên view
    }

    @GetMapping("/{myArea}")
    public String getGarden(@PathVariable("myArea") String myArea, @RequestParam("room") int room, Model model) {
        model.addAttribute("title", myArea);
        model.addAttribute("roomNumber", room);
        return "home";
    }
}
