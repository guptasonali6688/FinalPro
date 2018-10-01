package com.zycus.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_QUESTIONS")
public class Question {
	
	@Id
	@GeneratedValue
	private int Qid;
	private String ques;
	
	@OneToMany(mappedBy="question",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Response> responses;
	
	@ManyToOne
	@JoinColumn(name="surveyID")
	private Survey survey;

	public int getQid() {
		return Qid;
	}

	public void setQid(int qid) {
		Qid = qid;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public Set<Response> getResponses() {
		return responses;
	}

	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Question [Qid=" + Qid + ", ques=" + ques + ", survey=" + survey + "]";
	}

	

}
