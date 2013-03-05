<%@include file="../include/header.jsp" %>

<%@page import="model.Group"%>

<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Person</title>
</head>
<body>
<h2>View Person</h2>

<form action="EditGroup" method="POST">
	<%
	Group group = (Group)request.getAttribute("group");
	String warning = (String)request.getAttribute("warning");
	%>
	<% if(warning != null) { %><b>Message: </b> <%=warning %><br/> <% } %>
	Group ID: 			<%= group.getId()%> 	<br />
						<input type="hidden" name="group_id"  	id="group_id"    value="<%= group.getId()%>"> 
	Group Name: 		<input type="text" 	 name="name" 		id="name"		 value="<%= group.getName()%>" /><br/>
	Group Description: 	<input type="text" 	 name="description" id="description" value="<%= group.getDescription()%>" /> <br />
	Group Version: 		<%= group.getVersion()%> <br />
						<input type="hidden" name="version" 	id="version" 	 value="<%= group.getVersion()%>">
						

	<input type="submit" value="Submit" /> </br>
</form>
</body>	

<%@include file="../include/footer.jsp" %>
</html>