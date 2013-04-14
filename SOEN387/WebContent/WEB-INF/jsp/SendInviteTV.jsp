<%@page import="java.util.List"%>
<%@page import="dom.model.invite.Invite"%>
<%@include file="../include/header.jsp" %>

	<h2> SEND INVITE </h2>

	<div id="container">
	
		<ul>	
			<!-- change to permalink
			<li><a href="front?command=app.dispatcher.SendInviteDispatcher">Send Invite</a></li>
			-->
			<li><a href="<%out.print(strAbsolutePath + "send_invite"); %>">Send Invite</a></li>
		</ul>	
	
		<c:if test="${!empty error}">
			<p id="error"> ${error} </p>
		</c:if>
		<c:if test="${!empty info}">
			<p id="info"> ${info} </p>
		</c:if> 
		<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
		</c:if> 
	
		<div id="sendInviteContainer">
			<div id="sendInvite">
				<!-- change to permalink
				<form action="front?command=app.dispatcher.SendInviteDispatcher" method="POST" target="_self">
				-->
				<form action="send_invite" method="POST" target="_self">
					<!--span>USER ID </span> <input type="text" name="user_id" /> </br>
					<span>GROUP ID </span> <input type="text" name="group_id" value="${currentUser.group.id}"/> </br>
					<input type="submit" value="SEND INVITE" /> </br-->
				
					<table>
							<tr>
								<td><span>USER ID </span></td>
								<td><input type="text" name="user_id" /></td>
							</tr>
							<tr>
								<td><span>GROUP ID </span></td>
								<td><input type="text" name="group_id" value="${currentUser.group.id}"/></td>
							</tr>
							<tr>
								<td><input type="submit" value="SEND INVITE" /> </td>
							</tr>
					</table>
				
				</form>
			</div>
		</div>
		<br />
		
		<!-- change to permalink 
		<a href="front?command=app.dispatcher.BrowseInvitesDispatcher">Back</a>
		-->
		<a href="browse_invite">Back</a>
	</div>
	
<%@include file="../include/footer.jsp" %>