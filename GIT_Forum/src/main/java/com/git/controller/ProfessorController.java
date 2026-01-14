package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.service.ExamService;
import com.git.enities.Exam;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ExamService examService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);

        model.addAttribute("totalStudents", 0);

        model.addAttribute("liveExams", examService.getLiveExams().size());

        model.addAttribute("resultsPublished", examService.getPublishedResultsCount());

        model.addAttribute("professorName", "Prof. Sharma");

       

        return "professor/dashboard";
    }

   
}
