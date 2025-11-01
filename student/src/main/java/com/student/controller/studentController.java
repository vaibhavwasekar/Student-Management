package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.Student;
import com.student.repositry.studentRepositry;

@Controller
@RequestMapping("/student")
public class studentController {

    @Autowired
    private studentRepositry repo;

    // ✅ Home page — list all students
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", repo.findAll());
        return "index";
    }

    // ✅ Add / Edit student form
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("student", 
            id != null ? repo.findById(id).orElse(new Student()) : new Student());
        return "student_form";
    }

    // ✅ Save student (POST)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/student/";  // redirect back to home page
    }

    // ✅ Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/student/";  // redirect back to list page
    }
}
