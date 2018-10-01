package com.zycus.Controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.DTO.UserDetail;
import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;
import com.zycus.entity.*;
import com.zycus.service.SurveyServiceImpl;
import com.zycus.service.SurveyServiceImplInterface;


@Controller
@RequestMapping("/Survey/*")
public class AdminController {
	
	
	@Autowired
	private SurveyServiceImplInterface SurveyServiceImpl;
	
	@RequestMapping(value = "/newAdmin", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



	public @ResponseBody String add(@RequestBody  SurveyUser user)
	{

		SurveyServiceImpl.registerUser(user);
		return "User record created successfully";
		
		
	}
	
	
	@RequestMapping(value = "/newSurvey", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	
	public @ResponseBody String add(@RequestBody Survey survey, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		SurveyUser user = (SurveyUser)session.getAttribute("user");
		survey.setSurveyUser(user);
		System.out.println(survey.getQuestion().toString());
		SurveyServiceImpl.registerSurvey(survey);
		return "Survey created successfully";
	}
	
	
	@RequestMapping(value = "/SurveyView", method = RequestMethod.GET)
	
	public String SurveyViewCon()
	{
		
		
		return "SurveyView";
		
	}
	
@RequestMapping(value = "/Question", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	
	public @ResponseBody String QuestionViewCon(@RequestBody Survey survey,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		System.out.println("Working");		
		session.setAttribute("survey", SurveyServiceImpl.GetSurvey(survey));
		return "Question";
		
	}

@RequestMapping(value = "/Question1", method = RequestMethod.GET)

public  String QuestionView()
{
	
			System.out.println("Questions");
	
	return "Question";
	
}

@RequestMapping(value = "/Status", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



public @ResponseBody String ChangeStatus(@RequestBody  Survey survey)
{

	SurveyServiceImpl.updateStatus(survey);
	return "status updated successfully";

	
	
}


@RequestMapping(value = "/Shareit", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



public @ResponseBody String ShareSurvey(@RequestBody  Survey survey, HttpServletRequest request)
{

	HttpSession session = request.getSession();
	List<SurveyUser> userList=SurveyServiceImpl.getallUsers();
	List<ShareSurvey> shareSurvey=(List<ShareSurvey>)SurveyServiceImpl.getUsersFromSurvey(survey);
	List<SurveyUser> actualuserlist=new LinkedList(); 
	for(ShareSurvey a:shareSurvey){
   		for(SurveyUser user:userList){
   			if(user.getUid()!=a.getUser().getUid()){
   				actualuserlist.add(user);
   				System.out.println("UserList returned by the Admin Controller");
   				System.out.println(user);
   			}
   		}
   	}
   	
	session.setAttribute("usersurvey", actualuserlist);
	System.out.println("Working");		
	session.setAttribute("survey", survey);
	return "Select Users";

	
	
}

@RequestMapping(value = "/sharesurvey", method = RequestMethod.GET)

public  String ShareSurveyUser()
{
	
	return "ShareSurvey";
	
}


@RequestMapping(value = "/Survey/ShareWithUser", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")  

public @ResponseBody String ShareWithUser(@RequestBody  SurveyUser surveyuser, HttpServletRequest request)
{

	HttpSession session = request.getSession();
	Survey survey=(Survey)session.getAttribute("survey");
	SurveyUser admin=(SurveyUser)session.getAttribute("user");
	
	
			SurveyServiceImpl.addSurvey(surveyuser,admin,survey);
		
	
	
	
	
	System.out.println("Controller Implementation");
	
	return "Users Added";

	
	
}


@RequestMapping(value = "/Survey/SurveyResponseView", method = RequestMethod.GET)  

public String SurveyResponseView(HttpServletRequest request)
{

	
	return "SurveyViewReponse";

	
	
}


@RequestMapping(value = "/ShowQuestions", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")

public @ResponseBody String ShowQuestions(@RequestBody Survey survey,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");		
	session.setAttribute("survey", SurveyServiceImpl.GetSurvey(survey));
	return "Question";
	
}



@RequestMapping(value = "/Questiondisplay", method = RequestMethod.GET)

public  String Questiondisplay()
{
	
			System.out.println("Questions");
	
	return "RespondedQuestion";
	
}

/*@RequestMapping(value = "/ShowResponses", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")

public @ResponseBody String ShowResponses(@RequestBody Question question,HttpServletRequest request)
{
	HttpSession session = request.getSession();
	System.out.println("Working");		
	session.setAttribute("", SurveyServiceImpl.GetResponse(question));
	return "Question";
	
}

*/


@RequestMapping(value = "/Survey/SurveyResponseViewer", method = RequestMethod.GET)  

public String SurveyResponseView1(HttpServletRequest request)
{

	HttpSession session = request.getSession();
	
	SurveyUser admin=(SurveyUser)session.getAttribute("user");
	
	session.setAttribute("sharedsurveyAdmin", SurveyServiceImpl.getShareSurveyByAdmin(admin));
	
	
	
	System.out.println("Controller Implementation");
	
	return "AdminResponseViewer";

	
	
}

@RequestMapping(value = "/Survey/QuestionResponsesAdmin", method = RequestMethod.POST,consumes = "application/json", produces = "text/plain")  

public @ResponseBody String QuestionResponsesAdmin(@RequestBody ShareSurvey sharedsurvey,HttpServletRequest request)
{

	HttpSession session = request.getSession();
	
	
	
	session.setAttribute("sharedsurveyAdminresponse", SurveyServiceImpl.getResponsesForAdmin(sharedsurvey));
	
	
	return "QuestionAnswer";

	
	
}

@RequestMapping(value = "/Survey/QuestionAnswer", method = RequestMethod.GET)  

public String QuestionAnswer(HttpServletRequest request)
{

	
	
	
	
	return "QuestionAnswer";

	
	
}






	
}
