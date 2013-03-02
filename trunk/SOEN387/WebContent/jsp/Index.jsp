<%@include file="../include/header.jsp" %>

<h1>INDEX</h1>

<% if(request.getParameter("username") == null){
	%>
	<p> YOU SHOULD PROBABLY LOG IN </p>
	<a href="LogInPC" > CLICK HERE TO LOGIN </a>
	<%
	} else{
	%>
	<p> HEY <% request.getParameter("username"); %> </p>
	<%
	}
	%>

<%@include file="../include/footer.jsp" %>