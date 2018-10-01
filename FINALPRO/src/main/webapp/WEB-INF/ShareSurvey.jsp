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
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<script src="../jquery-3.3.1.min.js"></script>

<script type="text/javascript">

</script>
<script>

function ShareSurvey(el)
{
	
	
	var user_id = $(el).attr("id").split("_")[0];
	

		alert("HI")
		var SurveyUserObj= {
				
				uid:user_id
			}
		
		var SurveyUserObjAsJSON=JSON.stringify(SurveyUserObj);
		alert(SurveyUserObjAsJSON)
		
		
		 $.ajax({
			url:"/Survey/ShareWithUser",
			type:"POST",
			data:SurveyUserObjAsJSON,
			contentType:"application/json",
			success:function(response,status)
			{
				alert(response)
				window.location="/Survey/SurveyView";
			}
			}) 
		}
	
	
	


</script>
</head>
<body>
<% 

List<SurveyUser> surveyuser=(List<SurveyUser>)session.getAttribute("usersurvey");


List<ShareSurvey> sharesurvey=(List<ShareSurvey>)session.getAttribute("sharesurveyList");


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
			<h2>SHARE SURVEY TO</h2>			
			<span class="byline">USER LIST</span>
		 <form>
					<br><br><br>
					<table cellpadding="10" align="center" border="2">
   					<tr>
        
        			<td>USER ID</td>
        			<td>USER NAME</td>
        			<td>USER CONTACT</td>
        			
        
   					</tr>
   					<%for (SurveyUser f:surveyuser){
	   				%>
     
          			 <tr>
          			 <td><%= f.getUid()%></td>
           			 <td><%= f.getUname()%></td>
           			 <td><%= f.getUemail()%></td>
           			 
          			
					
           			
					<td><input type="button" value="CHECK"  name="GO"  onclick="ShareSurvey(this);" id="<%=f.getUid()%>" class="btn"/></td>
					
					</tr>

  					 <%
   					
  					 }   
   
  					 %>
  					 
  					 
   					</table>
   					
   					<a  align="center" class ="btn" href="SurveyView.jsp"><button class ="btn"  type="button" >Back</button></a>
  
   					</form>
		</div>

</div>
</div>
</body>
</html>