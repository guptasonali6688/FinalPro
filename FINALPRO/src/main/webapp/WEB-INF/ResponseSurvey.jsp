<!DOCTYPE html>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zycus.*" %>
<%@ page import="com.zycus.entity.*" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.js"
    src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script>

function SubmitResponse(el)
{
	var rowCount = $('#formfit tr').length;
	console.log(rowCount);
	
	
	var questionObj={
			responses:[]
	}
	

for(var j=0;j<rowCount-1;j++)
	{
    
	var responseObj = {
  		  question: {qid:$("#quesid_" + (j+1)).text().trim()},
  				
  		ranswer: $("#quesbox_" + (j+1)).val() 
	            } 
	questionObj.responses.push(responseObj);

	}
	



var questionObjAsJSON = JSON.stringify(questionObj);
alert(questionObjAsJSON);
console.log(questionObjAsJSON);

$.ajax({
  url: "/Survey/createResponses",
  method: "POST",
  data: questionObjAsJSON,
  contentType: "application/json",
  success: function (response, status) {
	  if(response=="Success")
		{
		window.location="/UserWindow.html"
		}
   
  }
});

}

</script>



<body>

<%

Survey survey=(Survey) session.getAttribute("survey");

Set<Question> question=survey.getQuestion();





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
			<h2>Questions</h2>			
			<span class="byline">USER Questions</span>
		 <form>
					<br><br><br>
					<table id="formfit" cellpadding="10" align="center" border="2">
   					<tr>
        
        			<td>QUESTION ID</td>
        			<td>QUESTION</td>
        			<td>RESPONSE</td>
        			
        
   					</tr>
   					<%int i=1; %>
   					<%for (Question f:question){
   						
	   				%>
     					
          			 <tr>
          			 <td id=<%="quesid_" +i   %>><%= f.getQid() %></td> 
           			 <td><%= f.getQues()%></td>
           			  			
           			 <td><input name="id" type="text" id= <%="quesbox_" +i   %> /><br /> 
           			 
          			
						<%i++; %>
           			
					
					
					</tr>

					

  					 <%
  					 }   
   
  					 %>
  					
  					 
   					</table>
   					 <input type="button" value="Submit"  name="GO"  onclick="SubmitResponse(this);" id=class="btn"/>
   					<a  align="center" class ="btn" href="SurveyView.jsp"><button class ="btn"  type="button" >Back</button></a>
  
   					</form>
		</div>

</div>
</div>

</body>
</html>
