<%@include file="../include/header.jsp" %>

<%@page import="model.Group" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
for(Group group: groups) {
%><a href="ViewGroupPC?id=<%= group.getId() %>"><%= group.getName() %></a><br/>
<% } %>
</body>

<%@include file="../include/footer.jsp" %>
</html>