package com.git.Professor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.Professor.Entity.Questionpaper;
import com.git.Professor.Repository.QuestionpaperRepositry;

@Service
public class QuestionpaperService {

	@Autowired
	QuestionpaperRepositry qr;

	public void savequestionpaper(Questionpaper questionpaper) {

		qr.save(questionpaper);

	}

}
