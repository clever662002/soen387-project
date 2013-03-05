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

<%
Group group = (Group)request.getAttribute("group");
%>
Group ID: 			<%= group.getId()%> <br /> 
Group Name: 		<%= group.getName()%><br/>
Group Description: 	<%= group.getDescription()%> <br />
Group Version: 		<%= group.getVersion()%> <br />

<a href="EditGroup?group_id=<%= group.getId() %>&version=<%= group.getVersion() %>"> Edit Group</a><br/><br/>
<a href="RemoveGroup?group_id=<%= group.getId()%>">Remove Group</a><br/><br/>
<a href="BrowseGroup">Browse All Groups</a><br/>
</body>	

<%@include file="../include/footer.jsp" %>
</html>