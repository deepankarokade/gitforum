package com.git.Professor.Entity;


import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="questionpaper")
public class Questionpaper {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qid;
	private String coursename;
	private String subject;
	private String chapter;
    private int duration;
    private String difficulty;
    
    // question paper image 
    private String questionpaperphotoname;
	@Lob
	@Column(name = "questionpaperimage", columnDefinition="LONGBLOB")
	private byte [] questionpaperimage;

	
	
    private String explanation;   
 
    
    // attachment 

    private String attachmentname;
	@Lob
	@Column(name = "attachment", columnDefinition="LONGBLOB")
	private byte [] attachment;

	
	public Questionpaper() {
		super();
	}


	public Questionpaper(int qid, String coursename, String subject, String chapter, int duration, String difficulty,
			String questionpaperphotoname, byte[] questionpaperimage, String explanation, String attachmentname,
			byte[] attachment) {
		super();
		this.qid = qid;
		this.coursename = coursename;
		this.subject = subject;
		this.chapter = chapter;
		this.duration = duration;
		this.difficulty = difficulty;
		this.questionpaperphotoname = questionpaperphotoname;
		this.questionpaperimage = questionpaperimage;
		this.explanation = explanation;
		this.attachmentname = attachmentname;
		this.attachment = attachment;
	}


	@Override
	public String toString() {
		return "Questionpaper [qid=" + qid + ", coursename=" + coursename + ", subject=" + subject + ", chapter="
				+ chapter + ", duration=" + duration + ", difficulty=" + difficulty + ", questionpaperphotoname="
				+ questionpaperphotoname + ", questionpaperimage=" + Arrays.toString(questionpaperimage)
				+ ", explanation=" + explanation + ", attachmentname=" + attachmentname + ", attachment="
				+ Arrays.toString(attachment) + "]";
	}


	public int getQid() {
		return qid;
	}


	public void setQid(int qid) {
		this.qid = qid;
	}


	public String getCoursename() {
		return coursename;
	}


	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getChapter() {
		return chapter;
	}


	public void setChapter(String chapter) {
		this.chapter = chapter;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


	public String getQuestionpaperphotoname() {
		return questionpaperphotoname;
	}


	public void setQuestionpaperphotoname(String questionpaperphotoname) {
		this.questionpaperphotoname = questionpaperphotoname;
	}


	public byte[] getQuestionpaperimage() {
		return questionpaperimage;
	}


	public void setQuestionpaperimage(byte[] questionpaperimage) {
		this.questionpaperimage = questionpaperimage;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public String getAttachmentname() {
		return attachmentname;
	}


	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}


	public byte[] getAttachment() {
		return attachment;
	}


	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	
	
	
	
	

}
