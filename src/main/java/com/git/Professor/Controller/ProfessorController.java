package com.git.Professor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.Professor.Entity.Exam;
import com.git.Professor.Service.ExamService;

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

        model.addAttribute("upcomingExams", examService.countUpcomingExams());

        model.addAttribute("liveExams", examService.countTodayExams());

        model.addAttribute("resultsPublished", examService.getPublishedResultsCount());

        model.addAttribute("professorName", "Prof. Sharma");

       

        return "professor/dashboard";
    }
    
    @GetMapping("/exams")
    public String viewAllExams(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "professor/Examlist";  
    }

   
}
