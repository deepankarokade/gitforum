package com.git.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.enities.Exam;
import com.git.repositories.ExamRepository;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepo;

    public Exam saveExam(Exam exam) {
        return examRepo.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepo.findById(id).orElse(null);
    }

    public void deleteExam(Long id) {
        examRepo.deleteById(id);
    }

    
    public List<Exam> getLiveExams() {
        return examRepo.findAll()
                .stream()
                .filter(e -> "Upcoming".equalsIgnoreCase(e.getStatus()))
                .collect(Collectors.toList());
    }

    public int getPublishedResultsCount() {
        return (int) examRepo.findAll()
                .stream()
                .filter(e -> "Published".equalsIgnoreCase(e.getStatus()))
                .count();
    }
}
