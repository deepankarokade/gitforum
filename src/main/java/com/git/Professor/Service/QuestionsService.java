package com.git.Professor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.Professor.Entity.Questions;
import com.git.Professor.Repository.QuestionRepositry;

@Service
public class QuestionsService {

    @Autowired
    QuestionRepositry qr;

    public void savequestions(Questions question) {

        qr.save(question);

    }

}
