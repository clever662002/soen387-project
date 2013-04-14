<%@include file="../include/header.jsp" %>

	<h2> INVITES </h2>
	
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
		
		<c:if test="${empty invites}">
			<p>You have no invites!</p>
		</c:if>
		
		<c:if test="${!empty invites}">	
		
			<div id="display">
				<table>
					<tr><!--td>Id</td-->
						<td>ID</td>
						<td>Group Name </td>
						<td>Description</td>
						<td></td><td></td>
					</tr>
				<c:forEach items="${invites}" var="invite">
					<tr>
						<!--td>${invite.id}</td-->
						<td>${invite.group.id}</td>
						<td>${invite.group.name}</td>
						<td>${invite.group.description}</td>
						
						<!-- change to permalink 
						<td><a href="front?command=app.dispatcher.AcceptInviteDispatcher&group_id=${invite.group.id}">YES</a></td>
						<td><a href="front?command=app.dispatcher.DeclineInviteDispatcher&group_id=${invite.group.id}">NO</a></td>						
						<td><a href="accept_invite/${invite.group.id}">YES</a></td>																	
						<td><a href="decline_invite/${invite.group.id}">NO</a></td>
						-->
						<td><a href="<%out.print(strAbsolutePath + "accept_invite/");%>${invite.group.id}">YES</a></td>
						<td><a href="<%out.print(strAbsolutePath + "decline_invite/");%>${invite.group.id}">NO</a></td>
																	
						
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</c:if>
<%@include file="../include/footer.jsp" %>