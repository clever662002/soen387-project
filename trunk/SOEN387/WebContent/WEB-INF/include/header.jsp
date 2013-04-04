<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>GROUP FORMER</title>
</head>
<body>

<h3> HEADER GOES HERE </h3>

<div id="navBarContainer">
	<ul id="navBar">
		
		<c:if test="${!empty sessionScope[\"currentUser\"]}">
			<p> THAT WORKED </p>
		</c:if>
		
		
		
		<% if(request.getSession().getAttribute("currentUser") != null){
		  %><li><a href="front?command=app.dispatcher.BrowseGroupDispatcher">GROUP</a></li>
			<li><a href="front?command=app.dispatcher.BrowseInvitesDispatcher">INVITES</a></li>
			<li><a href="front?command=app.dispatcher.LogoutDispatcher">LOGOUT</a></li>			
			<% 
			if(request.getSession().getAttribute("currentUser") != null){
				%><li><a href="Admin">ADMIN</a></li><%
			}
			
			}
		else{ %>
			<li><a href="Login">LOGIN</a></li><%
		}
		 %>
	</ul>
</div>