package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.git.enities.Exam;
import com.git.service.ExamService;



@Controller
@RequestMapping("/professor/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

  
    @GetMapping("/create")
    public String createExamForm(Model model) {
        model.addAttribute("exam", new Exam());
        return "professor/exam-create"; 
    }


   
    @PostMapping("/save")
    public String saveExam(@ModelAttribute("exam") Exam exam) {
    
        exam.setStatus("Upcoming");

      
        examService.saveExam(exam);


        return "redirect:/professor/dashboard";
    }

   
    @GetMapping
    public String viewAllExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "exam-list"; 
    }

  
    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return "redirect:/professor/exams";
    }
}
