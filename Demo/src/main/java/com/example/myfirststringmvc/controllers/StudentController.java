package com.example.myfirststringmvc.controllers;

import com.example.myfirststringmvc.models.Student;
import com.example.myfirststringmvc.services.IStudentService;
import com.example.myfirststringmvc.services.StorageService;
import com.example.myfirststringmvc.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    IStudentService studentService;
    @Autowired
    StorageService storageService;
    @GetMapping("/students")   //danh sách sinh viên
    public ModelAndView getStudents(
            @RequestParam(name = "q", defaultValue = "") String q,
            @RequestParam(name = "sort",defaultValue = "code") String sort,
            @RequestParam(name = "dir",defaultValue = "asc") String dir
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "5") int size,

    ) {
        List<Student> students = studentService.findAll(q, sort, dir);
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("students", students);
        modelAndView.addObject("q", q);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("dir", dir);
        return modelAndView;
    }
    @GetMapping("/students/{id}")
    public ModelAndView getStudent(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("detail-student");
        modelAndView.addObject("student", studentService.getStudentById(id));
        return modelAndView;
    }
    @GetMapping("/students/create") //trả về form thêm sinh viên
    public String getStudentCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }
    @PostMapping("/students/create")
    public String createStudent(@Valid Student student,
                                @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "student-form"; //giữ nguyên view chứ k redirect về danh sách sinh viên
        }
        //xuwr ly upload file
        if(avatarFile!=null){
            //lưu file vào thư mục upload (StorageService)
            try {
                //lấy đường dẫn tương đối  -->String publicUrl
                String publicUrl = storageService.saveFile(avatarFile);
                student.setAvatar(publicUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //student.setAvatar(publicUrl)
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Student created successfully");
        return "redirect:/students";
    }

}
