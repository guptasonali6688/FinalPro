package com.zycus.service;

import java.util.List;

import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.ShareSurvey;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;

public interface SurveyServiceImplInterface {
	
	public void registerUser(SurveyUser user);
	
	public int SurveyLogIn(SurveyUser surveyUser);
	
	public SurveyUser surveyList(int userid);
	
	
	
	
	public void registerSurvey(Survey survey);
	
	
	public Survey GetSurvey(Survey survey);
	


	public void updateStatus(Survey survey);
	
	


	public List<SurveyUser> getallUsers();


	public void addSurvey(SurveyUser surveyuser,SurveyUser admin, Survey survey);


	public List<ShareSurvey> GetUserSurvey(SurveyUser user);


	public void addResponse(Response response);


	public List<Response> GetResponse(SurveyUser user);
	
	
	public List<Survey> GetUsersSurveys(SurveyUser user);


	public List<Survey> GetUsersSurvey(SurveyUser user);


	public List<Response> GetUsersResponses(Survey survey,SurveyUser user);
	

	public List<Response> GetUsersResponse(SurveyUser user);
	
	

	public SurveyUser getUserFromID(int uid);
	
	

	public void ADDUser(SurveyUser user);


	public List<ShareSurvey> GetUseShareSurvey(SurveyUser user);
	
	
	public void ChangeSharedSurvey(SurveyUser user,Survey survey);
	


	public List<ShareSurvey> getShareSurveyByAdmin(SurveyUser admin);

	public List<Response> getResponsesForAdmin(ShareSurvey sharedsurvey);
	
	
	public List<ShareSurvey> getUsersFromSurvey(Survey survey);

}
