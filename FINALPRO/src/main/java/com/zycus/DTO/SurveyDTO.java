package com.zycus.DTO;

import java.util.HashMap;
import java.util.Map;

public class SurveyDTO {
	
	private int SurveyId;
	private Map<Integer, Integer> cart=new HashMap<Integer,Integer>();
	
	public void addToCart(int QuestionID,int ResponseID)
	{
		cart.put(QuestionID, ResponseID);
	}

	public int getSurveyId() {
		return SurveyId;
	}

	public void setSurveyId(int surveyId) {
		SurveyId = surveyId;
	}

	public Map<Integer, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, Integer> cart) {
		this.cart = cart;
	}
	
	

}
