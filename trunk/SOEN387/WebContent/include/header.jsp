<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
</head>
<body>

<h1> HEADER GOES HERE </h1>

<div id="navBarContainer">
	<ul id="navBar">
		<% if(request.getSession().getAttribute("username") != null){
		  %><li><a href="Group">GROUP</a></li>
			<li><a href="Invite">INVITES</a></li>
			<li><a href="Logout">LOGOUT</a></li>
			<%
			}
		else{ %>
			<li><a href="Login">LOGIN</a></li><%
		}
		 %>
	</ul>
</div>