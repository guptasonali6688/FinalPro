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

<script type="text/javascript"></script>



</head>
<body>
<% 

List<Response> responses=(List<Response>) session.getAttribute("sharedsurveyAdminresponse");



if(responses==null)
{
	out.println("Null Survey");
	return;
}



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
        
        			
        			<td>QUESTION</td>
        			<td>RESPONSE</td>
        			
        			
        
   					</tr>
   					<%for (Response f:responses){
	   				%>
     
          			 <tr>
          			 
           			 <td><%= f.getQuestion().getQues()%></td>
           			 <td><%= f.getRanswer()%></td>
          			

           			
					
					
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