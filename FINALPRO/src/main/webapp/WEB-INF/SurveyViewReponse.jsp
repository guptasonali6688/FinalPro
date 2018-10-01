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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">

form
{

}
div.container
{
text-align: center;
}

</style>

<script>

function ShowResponses(el)
{
	
	
	var survey_id = $(el).attr("id").split("_")[0];
	var status = $("#"+survey_id+"_status").val();

	if(status=="false")
		{
		alert("Inactive Survey");
		}
	else
		{
		var SurveyShareObj= {
				
				sid:survey_id,
				sstatus:status
			}
		
		var SurveyShareObjAsJSON=JSON.stringify(SurveyShareObj);
		alert(SurveyShareObjAsJSON)
		
		
		 $.ajax({
			url:"/Survey/ShowQuestions",
			type:"POST",
			data:SurveyShareObjAsJSON,
			contentType:"application/json",
			success:function(response,status)
			{
				alert(response)
				window.location="/Survey/Questiondisplay";
			}
			}) 
		}
	
	
	
}

</script>
</head>
<body>
<% 

SurveyUser surveyUser=(SurveyUser) session.getAttribute("user");

Set<Survey> survey=new HashSet<Survey>();
survey=surveyUser.getSurvey();

if(survey==null)
{
	out.println("Null Survey");
	return;
}

Set<Survey> survey1= surveyUser.getSurvey();
session.setAttribute("survey", survey1);

%>
<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1 >HELLO <% surveyUser.getUname(); %><h1>
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
        
        			<td>SurveyID</td>
        			<td>Survey Name</td>
        			<td>Survey Status</td>
        			<td>fbdfj </td>
       				 <td>Show Reponses</td>
   					</tr>
   					<%for (Survey f:survey){
	   				%>
     
          			 <tr>
          			 <td><%= f.getSid()%></td>
           			 <td><%= f.getSname()%></td>
           			 
          			
					<td><input type="button" value="<%= f.getSstatus()%>" name="GO"  onclick="changeStatus(this);" id=<%=f.getSid()+"_status"%> class="btn"/></td>
           			
					<td><input type="button" value="GO" name="GO"  onclick="change(this);" id=<%=f.getSid()%> class="btn"/></td>
					
					<td><input type="button" value="Share Survey" name="GO"  onclick="ShowResponses(this);" id=<%=f.getSid()+"_share"%> class="btn"/></td>
					
					</tr>

  					 <%
  					 }   
   
  					 %>
  					 
  					 
   					</table>
   					
   					<a  class ="btn" href="Survey.html"><button class ="btn"  type="button" >Back</button></a>
  
   					</form>
		</div>

</div>
</div>
</body>
</html>