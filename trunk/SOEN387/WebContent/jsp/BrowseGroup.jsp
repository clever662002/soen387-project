<%@page import="model.Group"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Group</title>
</head>
<body>
<%
List<Group> groups = (List<Group>)request.getAttribute("group");
for(Group gr: groups) {
%><a href="ViewPersonPC?id=<%= gr.getName() %>"><%= gr.getDescription() %></a><br/>
<% } %>
	
</body>
</html>