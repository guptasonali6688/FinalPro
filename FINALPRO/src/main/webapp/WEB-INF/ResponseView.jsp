<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zycus.*" %>
<%@ page import="com.zycus.entity.*" %>


<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Didact+Gothic"
	rel="stylesheet" />

<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey View</title>

<style type="text/css">

form
{

}

</style>
<script src="../jquery-3.3.1.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<script type="text/javascript">

</script>
<script>

function GetQuestions(el)
{
	
	
	var survey_id = $(el).attr("id").split("_")[0];
	

	
	
		var UserSurveyObj= {
				
				sid:survey_id,
				
			}
		
		var UserSurveyObjAsJSON=JSON.stringify(UserSurveyObj);
		alert(UserSurveyObjAsJSON)
		
		
		 $.ajax({
			url:"/Survey/QuestionResponses",
			type:"POST",
			data:UserSurveyObjAsJSON,
			contentType:"application/json",
			success:function(response,status)
			{
				if(response=="TOSURVEY")
					{
					window.location="/Survey/TotheSurvey"
					}
				
			}
			}) 
		
	
	
	
}

</script>

</head>
<body>
<% 



List<Survey> survey=(List<Survey>)session.getAttribute("survey");

List<ShareSurvey> shareSurvey=(List<ShareSurvey>)session.getAttribute("sharedsurvey");

%>
<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				
			</div>
</div>
</div>
<div id="wrapper">
	<div id="three-column" class="container">
		<div class="title">
			<h2>YOUR SURVEYS</h2>			
			<span class="byline">Survey Submitted</span>
		 <form>
					<br><br><br>
					<table cellpadding="10" align="center" border="2">
   					<tr>
        
        			<td>SURVEY ID</td>
        			<td>SURVEY NAME</td>
        			<td>RESPONSE</td>
        			
        			
        			
        
   					</tr>
   					<%for (ShareSurvey f:shareSurvey){
	   				%>
     				<%if(f.getSurvey().getSstatus().equals("true") && f.getStat().equals("attempted"))
     					{
     					%>
     					 <tr>
              			 <td><%= f.getSurvey().getSid()%></td>
               			 <td><%= f.getSurvey().getSname()%></td>
               			 
               			 
              			

               			
    					<td><input type="button" value="GO"  name="GO"  onclick="GetQuestions(this);"  id="<%=f.getSurvey().getSid()%>_submit" class="btn"/></td>
     					<%} %>
     					
     				
          			
					
					</tr>

  					 <%
  					 }   
   
  					 %>
  					 
  					 
   					</table>
   					
   					<a  class ="btn" href="SurveyView.jsp"><button class ="btn"  type="button" >Back</button></a>
  
   					</form>
		</div>

</div>
</div>
</body>
</html>