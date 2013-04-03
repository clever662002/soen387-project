<%@page import="java.util.List"%>
<%@page import="dom.model.invite.Invite"%>
<%@include file="../include/header.jsp" %>

	<h2> SEND INVITE </h2>
	
	<ul>	
		<li><a href="SendInvite">Send Invite</a></li>
	</ul>	
	
	<div id="container">
	
		<%
		if(request.getAttribute("info") != null){
			out.print("<p id=\"info\">"+request.getAttribute("info").toString()+"</p>");
		}
		if(request.getAttribute("error") != null){
			out.print("<p id=\"error\">"+request.getAttribute("error").toString()+"</p>");
		}
		%>
	
		<form action="SendInvite" method="POST" target="_self">
			<span>USER ID </span> <input type="text" name="user_id" /> </br>
			<span>GROUP ID </span> <input type="text" name="group_id" /> </br>
			<input type="submit" value="SEND INVITE" /> </br>
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>