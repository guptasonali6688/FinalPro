package com.zycus.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;
import com.zycus.entity.Response;
import com.zycus.service.UserServiceInterface;

@Controller
@RequestMapping("/Survey/*")
public class UserController {
	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
@RequestMapping(value = "/Survey/UserView", method = RequestMethod.GET)
	
	public String SurveyViewCon(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		System.out.println("Working");
		SurveyUser user=(SurveyUser)session.getAttribute("user");
		
		session.setAttribute("response", userServiceInterface.GetUsersResponse(user));
		
		session.setAttribute("surveyList", userServiceInterface.GetUsersSurvey(user));
		
		
		
		return "UserSurvey";
		
	}



@RequestMapping(value = "/Response", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")

public @ResponseBody String ResponseViewCon(@RequestBody Survey survey,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");		
	session.setAttribute("survey", userServiceInterface.GetSurvey(survey));
	System.out.println("response controller");
	return "Success";
	
}


@RequestMapping(value = "/ResponseSurvey", method = RequestMethod.GET)

public String ResponseSurvey(HttpServletRequest request)
{

	return "ResponseSurvey";
	
}


@RequestMapping(value = "/createResponses", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")

public @ResponseBody String createResponse(@RequestBody Question question,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	SurveyUser user = (SurveyUser)session.getAttribute("user");
	System.out.println(user);
	Survey survey=(Survey)session.getAttribute("survey");
	session.setAttribute("user", userServiceInterface.getUserFromID(user.getUid()));
	
	for(Response r:question.getResponses())
	{
		
		r.setSurveyUser(user);
		//user.getResponses().add(r);
		//userServiceInterface.ADDUser(user);
		r.setSurvey(survey);
//		response answer=new response();
//		answer.setRanswer(response.getRanswer());
//		answer.setSurveyUser(user);
		userServiceInterface.addResponse(r);

//		
//		Question question1=questionrepository.findQuestions(response.getQuestion().getQid());
//		answer.setQuestion(question1);
//		question1.getResponses().add(answer);
	
//		System.out.println(r);
	}
	
		userServiceInterface.ChangeSharedSurvey(user,survey);
	
	return "Success";
	
}


@RequestMapping(value = "/Survey/SurveyResponseViewUser", method = RequestMethod.GET)

public String SurveyResponseView(HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");	
	SurveyUser user=(SurveyUser)session.getAttribute("user");
	session.setAttribute("sharedsurvey", userServiceInterface.GetUseShareSurvey(user));
	session.setAttribute("survey", userServiceInterface.GetUsersSurvey(user));
	
	
	
	return "ResponseView";
	
}

/*@RequestMapping(value = "/Survey/SetResponse", method = RequestMethod.GET)

public String SurveyResponseView(@RequestBody Survey survey,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");	
	
	
	session.setAttribute("responseList", userServiceInterface.GetUsersResponses(((Survey)session.getAttribute("survey")),((SurveyUser)session.getAttribute("user"))));
	
	System.out.println("Hello");
	return "";
	
}*/


@RequestMapping(value = "/Survey/QuestionResponses", method = RequestMethod.POST,consumes = "application/json", produces = "text/plain")

public @ResponseBody String QuestionResponses(@RequestBody Survey survey,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");	
	
	
	session.setAttribute("Responses", userServiceInterface.GetUsersResponses(survey,((SurveyUser)session.getAttribute("user"))));
	
	System.out.println("Hello");
	return "TOSURVEY";
	
}


@RequestMapping(value = "/TotheSurvey", method = RequestMethod.GET)

public String TotheSurvey(HttpServletRequest request)
{

	return "RespondedQuestion";
	
}




	

}
