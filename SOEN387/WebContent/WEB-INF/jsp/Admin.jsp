<%@page import="utils.SecurityUtil"%>
<%@include file="../include/header.jsp" %>

<h2>ADMIN</h2>

<ul>
	<li><a href="AddUsers">ADD USERS</a></li>
</ul>

<% if(!SecurityUtil.isAuthenticated(request)){
	%>
	<p> YOU SHOULD PROBABLY LOG IN </p>
	<a href="Login" > CLICK HERE TO LOGIN </a>
	<%
	} else{
	%>
	<p> HEY <% out.print(request.getSession().getAttribute("username").toString().toUpperCase()); %> </p>
	<%
	}
	%>

<%@include file="../include/footer.jsp" %>