package com.zycus.service;

import java.util.List;

import com.zycus.entity.Response;
import com.zycus.entity.ShareSurvey;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;

public interface UserServiceInterface {
	
public List<Response> GetUsersResponse(SurveyUser user);

public List<Survey> GetUsersSurvey(SurveyUser user);

public Survey GetSurvey(Survey survey);


public SurveyUser getUserFromID(int uid);

public void addResponse(Response response);

public void ChangeSharedSurvey(SurveyUser user,Survey survey);

public List<ShareSurvey> GetUseShareSurvey(SurveyUser user);


public List<Response> GetUsersResponses(Survey survey,SurveyUser user);



}
