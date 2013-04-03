<%@include file="../include/header.jsp" %>


<%@page import="java.util.List,dom.model.group.Group"%>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Group</title>
</head>
<body>
<h2>Browse Group</h2>
<%
List<Group> groups = (List<Group>)request.getAttribute("group");
%>
<%
for(int i=0; i<groups.size(); i++) {
%><a href="ViewGroup?group_id=<%= groups.get(i).getId() %>"><%= groups.get(i).getName() %></a><br/>
<% } %> 
<br>
<a href="CreateGroup">Create New Group</a><br/>
</body>

<%@include file="../include/footer.jsp" %>
</html>