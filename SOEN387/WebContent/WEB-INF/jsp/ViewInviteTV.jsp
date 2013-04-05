<%@page import="dom.model.invite.Invite"%>
<%@include file="../include/header.jsp" %>

	<h2> INVITES </h2>
	
	<ul>	
		<li><a href="front?command=app.dispatcher.SendInviteDispatcher">Send Invite</a></li>
	</ul>	
		
	<c:if test="${!empty error}">
		<p id="error"> ${error} </p>
	</c:if>
	<c:if test="${!empty info}">
		<p id="info"> ${info} </p>
	</c:if> 
	
	<c:if test="${empty invites}">
		<p>You have no invites!</p>
	</c:if>
	<c:if test="${!empty invites}">
		<table><tr><td>Id</td><td>Group</td><td>Description</td><td></td><td></td></tr>
		<c:forEach items="${invites}" var="invite">
			<tr>
				<td>${invite.id}</td>
				<td>${invite.group.id}</td>
				<td>${invite.group.description}</td>
				<td><a href="front?command=app.dispatcher.AcceptInviteDispatcher&group_id=${invite.group.id}">Accept</a></td>
				<td><a href="front?commad=app.dispatcher.DeclineInviteDispatcher&group_id=${invite.group.id}">Decline</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>

	<%
/*
	List<Invite> invites = (List<Invite>)request.getAttribute("invites");
	if(invites != null && invites.size() > 0){
		out.print("<table><tr><td>Id</td><td>Group</td><td>Description</td><td></td><td></td></tr>");
		for(Invite invite : invites){
			out.print("<tr>");
			out.print("<td>"+invite.getId()+"</td>");
			out.print("<td>"+invite.getGroup().getName()+"</td>");
			out.print("<td>"+invite.getGroup().getDescription()+"</td>");
			out.print("<td><a href=\"AcceptInvite?group_id="+invite.getGroup().getId()+"\">Accept</a></td>");
			out.print("<td><a href=\"DeclineInvite?group_id="+invite.getGroup().getId()+"\">Decline</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
	else{
		out.print("<p> You have no invites. </p>");
	}
*/	
	%>
	
<%@include file="../include/footer.jsp" %>