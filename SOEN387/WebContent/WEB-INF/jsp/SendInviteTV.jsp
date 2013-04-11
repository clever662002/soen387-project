<%@page import="java.util.List"%>
<%@page import="dom.model.invite.Invite"%>
<%@include file="../include/header.jsp" %>

	<h2> SEND INVITE </h2>
	
	<ul>	
		<li><a href="front?command=app.dispatcher.SendInviteDispatcher">Send Invite</a></li>
	</ul>	
	
	<div id="container">
	
		<c:if test="${!empty error}">
				<p id="error"> ${error} </p>
			</c:if>
		<c:if test="${!empty info}">
			<p id="info"> ${info} </p>
		</c:if> 
		<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
		</c:if> 
	
		<form action="front?command=app.dispatcher.SendInviteDispatcher" method="POST" target="_self">
			<span>USER ID </span> <input type="text" name="user_id" /> </br>
			<span>GROUP ID </span> <input type="text" name="group_id" value="${currentUser.group.id}"/> </br>
			<input type="submit" value="SEND INVITE" /> </br>
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>