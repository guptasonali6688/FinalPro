package com.zycus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_RESPONSE6")
public class Response {
	
	@Id
	@GeneratedValue
	private int rid;
	private String ranswer;
	
	
	@ManyToOne
	@JoinColumn(name="questionID")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="userID")
	private SurveyUser surveyUser;
	
	
	@ManyToOne
	@JoinColumn(name="surveyID")
	private Survey survey;
	
	

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRanswer() {
		return ranswer;
	}

	public void setRanswer(String ranswer) {
		this.ranswer = ranswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public SurveyUser getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	@Override
	public String toString() {
		return "response [rid=" + rid + ", ranswer=" + ranswer + "]";
	}

	

	

	
	
}
