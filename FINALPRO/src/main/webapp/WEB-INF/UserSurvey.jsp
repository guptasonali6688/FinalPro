<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.zycus.service.*" %>
<%@ page import="com.zycus.entity.*" %> 
<%@ page import="com.zycus.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="../jquery-3.3.1.min.js"></script>
<script type="text/javascript"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
function change(el)
{
	
		var survey_id =$(el).attr("id"); 

	var SurveyObj= {
			
			sid:survey_id
		}
	
	var SurveyObjAsJSON=JSON.stringify(SurveyObj);
	alert(SurveyObjAsJSON)
	
	$.ajax({
		url:"/Survey/Response",
		type:"POST",
		data:SurveyObjAsJSON,
		contentType:"application/json",
		success:function(response,status)
		{
			if(response=="Success")
				{
				window.location="/Survey/ResponseSurvey"
				}
		}
		})
	
	}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Survey</title>
</head>
<body>
<% 


List<ShareSurvey> surveyUser=(List<ShareSurvey>) session.getAttribute("surveyList");

SurveyUser user=(SurveyUser)session.getAttribute("user");

%>

<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1 >HELLO<h1>
			</div>
</div>
</div>
<div id="wrapper" align="center">
	<div id="three-column" class="container">
		<div class="title">
			<h2>YOUR SURVEYS</h2>			
			<span class="byline">Survey To be Taken</span>
		 <form>
					<br><br><br>
					
					<table cellpadding="10"  border="2">
   					<tr>
        
        			
        			<td>Survey Name</td>
        			<td>Take Survey</td>
   					</tr>
   					<%for (ShareSurvey f:surveyUser){
   						
   						
	   				%>
     
          			 <tr>
          			 
           			 <td><%= f.getSurvey().getSname()%></td>
           			 <%
           			 
   						
        if (f.getSurvey().getSstatus().equals("true") && (f.getStat().equals("share"))) {
    %>
       <td><input type="button" value="GO" name="GO"  onclick="change(this);" id=<%=f.getSurvey().getSid()%> class="btn"/></td>

       
       <% 
    } 
        else {
    %> 
     <td><input type="button"  value="GO" disabled></td>
    <% 
        }
    %> 
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