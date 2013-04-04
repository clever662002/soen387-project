<%@page import="utils.SecurityUtil"%>
<%@include file="../include/header.jsp" %>

<h2>INDEX</h2>

<% if(!SecurityUtil.isAuthenticated(request)){
	%>
	<p> YOU SHOULD PROBABLY LOG IN </p>
	<a href="Login" > CLICK HERE TO LOGIN </a>
	<%
	} else{
	%>
	<p> HEY <% out.print(request.getSession().getAttribute("username")); %> </p>
	<%
	}
	%>

<%@include file="../include/footer.jsp" %>