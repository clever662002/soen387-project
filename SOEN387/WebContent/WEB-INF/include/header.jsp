<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>GROUP FORMER</title>
</head>
<body>

<h3> HEADER GOES HERE </h3>

<div id="navBarContainer">
	<ul id="navBar">
	
	<!--  THIS IS DIRTY, I KNOW !!  -->
	
		<c:if test="${empty sessionScope[\"currentUser\"]}">
			<li><a href="front?command=app.dispatcher.LoginDispatcher">LOGIN</a></li>
		</c:if>
		
		<c:if test="${!empty sessionScope[\"currentUser\"]}">
			<c:forEach items="${sessionScope[\"currentUser\"].roles}" var="role">
				<c:if test="${role.id == 1}">
					<li><a href="front?command=app.dispatcher.LoginDispatcher">LOGIN</a></li>
				</c:if>
				<c:if test="${role.id == 2}">
					<li><a href="front?command=app.dispatcher.BrowseInvitesDispatcher">INVITES</a></li>
					<li><a href="front?command=app.dispatcher.BrowseGroupDispatcher">GROUP</a></li>
					<li><a href="front?command=app.dispatcher.LogoutDispatcher">LOGOUT</a></li>
				</c:if>
				<c:if test="${role.id == 3}">
					<li><a href="front?command=app.dispatcher.AddUsersDispatcher">ADMIN</a></li>
				</c:if>
			</c:forEach>
		</c:if>
	</ul>
</div>