package com.zycus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_SHARESURVEY1")
public class ShareSurvey {
	
	@Id
	@GeneratedValue
	private int Shareid;
	private String stat;
	
	public String getStat() {
		return stat;
	}



	public void setStat(String stat) {
		this.stat = stat;
	}

	@ManyToOne
	private SurveyUser Admin;
	
	@ManyToOne
	private SurveyUser user;

	
	
	
	@ManyToOne
	private Survey survey;
	
	public Survey getSurvey() {
		return survey;
	}



	public void setSurvey(Survey survey) {
		this.survey = survey;
	}



	public int getShareid() {
		return Shareid;
	}

	
	
	public void setShareid(int shareid) {
		Shareid = shareid;
	}

	public SurveyUser getAdmin() {
		return Admin;
	}

	public void setAdmin(SurveyUser admin) {
		Admin = admin;
	}

	public SurveyUser getUser() {
		return user;
	}

	public void setUser(SurveyUser user) {
		this.user = user;
	}
	
	
	
	
	
	

}
