<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:url var="cssUrl" value="/css/main.css" />
	<link rel="stylesheet" type="text/css" href="${cssUrl}">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>GROUP FORMER</title>
</head>
<body>

<div id="header">
	<h1> GROUP FORMING WEBSITE EXTRAORDINAIRE </h1>
</div>

<div id="navBarContainer">
	<ul id="navBar">
	
	<!--  THIS IS DIRTY, I KNOW !!  -->
		
		<c:set var="thePath" value="http://${pageContext.request.serverName}:8080${pageContext.request.contextPath}/front/"/>

		<c:if test="${empty sessionScope[\"currentUser\"]}">
			<li><a href="front?command=app.dispatcher.LoginDispatcher">LOGIN</a></li>
		</c:if>
		
		<c:if test="${!empty sessionScope[\"currentUser\"]}">
			<c:forEach items="${sessionScope[\"currentUser\"].roles}" var="role">
				<c:if test="${role.id == 1}">
					<!-- 
					<li><a href="front?command=app.dispatcher.LoginDispatcher">LOGIN</a></li>
					-->
					<li><a href="${thePath}login">LOGIN</a></li>
				</c:if>
				<c:if test="${role.id == 2}">
					<!-- 					 
					<li><a href="front?command=app.dispatcher.BrowseInvitesDispatcher">INVITES</a></li>					 
					<li><a href="front?command=app.dispatcher.BrowseGroupDispatcher">GROUP</a></li>
					<li><a href="front?command=app.dispatcher.LogoutDispatcher">LOGOUT</a></li>
					-->                                         
					<li><a href="${thePath}browse_invite">INVITES</a></li>
					<li><a href="${thePath}browse_group">GROUP</a></li>					
					<li><a href="${thePath}logout">LOGOUT</a></li>					
					
					
				</c:if>
				<c:if test="${role.id == 3}">
					<!-- 
					<li><a href="front?command=app.dispatcher.AddUsersDispatcher">ADMIN</a></li>
					-->
					<li><a href="${thePath}add_user">ADMIN</a></li>
				</c:if>
			</c:forEach>
		</c:if>
	</ul>
</div>
<div id="divBody">