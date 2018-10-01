package com.zycus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.entity.Response;
import com.zycus.entity.ShareSurvey;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;
import com.zycus.repository.ShareSurveyrepository;
import com.zycus.repository.UserRepository;
import com.zycus.repository.questionRepository;
import com.zycus.repository.responseRepository;
import com.zycus.repository.surveyRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private surveyRepository surveyrepository;
	
	@Autowired
	private questionRepository questionrepository;
	
	@Autowired
	private responseRepository responserepository;
	
	@Autowired
	private ShareSurveyrepository shareSurveyrepository;
	
	
public List<Response> GetUsersResponse(SurveyUser user) {
		
		
		return responserepository.findResponses(user.getUid());
	}

public List<Survey> GetUsersSurvey(SurveyUser user) {
	
	
	return surveyrepository.findSurveys(user.getUid());
}


public Survey GetSurvey(Survey survey)
{
	
	Survey survey1=(surveyrepository.findById(survey.getSid())).get();
	System.out.println("Service Working");
	System.out.println(survey1);
	
	 return survey1;
}


public SurveyUser getUserFromID(int uid) {


	return userRepository.findById(uid).get();
	
}

public void addResponse(Response response) {
	
	
//	
//	System.out.println(question1);
	
	responserepository.save(response);
	
	
	
	
	
	
}


public void ChangeSharedSurvey(SurveyUser user,Survey survey)
{
	ShareSurvey sharesurvey=shareSurveyrepository.getrespondedharesurveys(user.getUid(),survey.getSid());
	sharesurvey.setStat("attempted");
	
}

public List<ShareSurvey> GetUseShareSurvey(SurveyUser user) {
	
	return shareSurveyrepository.findUsers(user.getUid());
}

public List<Response> GetUsersResponses(Survey survey,SurveyUser user) {
	
	
	return responserepository.getReponsesForSurvey(user.getUid(),survey.getSid());
}




}
