<%@page import="utils.SecurityUtil"%>
<%@include file="../include/header.jsp" %>

<h1>INDEX</h1>

<% if(SecurityUtil.isAuthenticated(request)){
	%>
	<p> YOU SHOULD PROBABLY LOG IN </p>
	<a href="Login" > CLICK HERE TO LOGIN </a>
	<%
	} else{
	%>
	<p> HEY <% request.getParameter("username"); %> </p>
	<%
	}
	%>

<%@include file="../include/footer.jsp" %>