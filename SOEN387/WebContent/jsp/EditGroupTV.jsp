<%@include file="../include/header.jsp" %>

<%@page import="model.Group"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Person</title>
</head>
<body>
<h2>View Person</h2>

<form action="EditGroup" method="POST" target="_self" enctype="multipart/form-data">
	<%
	Group group = (Group)request.getAttribute("group");
	String warning = (String)request.getAttribute("warning");
	%>
	<% if(warning != null) { %><b>Message: </b> <%=warning %><br/> <% } %>
	Group ID: 			<%= group.getID()%> 	<br /> 
	Group Name: 		<input type="text" name="name" 			value="<%= group.getName()%>" /><br/>
	Group Description: 	<input type="text" name="description" 	value="<%= group.getDescription()%>" /> <br />
	Group Version: 		<%= group.getVersion()%> <br />

	<input type="submit" value="Submit" /> </br>
</form>
</body>	

<%@include file="../include/footer.jsp" %>
</html>