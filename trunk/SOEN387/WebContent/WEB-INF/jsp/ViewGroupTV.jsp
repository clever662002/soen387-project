<%@include file="../include/header.jsp" %>

	<h2>View Group</h2>
	
	<div id="container">
		
		<c:if test="${!empty error}">
			<p id="error"> ${error} </p>
		</c:if>
		
		<c:if test="${empty group}">
			<p>You are not in a group!</p>
		</c:if>
			
		<c:if test="${!empty group}">
		
			<ul>
				<c:if test="${group.id == currentUser.group.id}">
					<!-- change to permalink
					<li><a href="front?command=app.dispatcher.EditGroupDispatcher&group_id=${group.id}">Edit Group</a></li>
					<li><a href="front?command=app.dispatcher.RemoveGroupDispatcher&group_id=${group.id}">Remove Group</a>	</li>
					-->				
					<li><a href="../edit_group/${group.id}">Edit Group</a></li>
					<li><a href="../remove_group/${group.id}">Remove Group</a>	</li>
				</c:if>
			</ul>
		
		
			<div id="display">
				<table>
					<tr>
						<td>Id</td>
						<td>${group.id}</td>
					</tr>
					<tr>
						<td>Group Name:</td>
						<td>${group.name}</td>
					</tr>
					<tr>
						<td>Description:</td>
						<td>${group.description}</td>
					</tr>							
				</table>
			</div>
		</c:if>
		</br>
		<!-- change to permalink 
		<a href="front?command=app.dispatcher.BrowseGroupDispatcher">Back</a>
		-->		
		<a href="<%out.print(strAbsolutePath + "browse_group"); %>">Back</a>
	</div>
<%@include file="../include/footer.jsp" %>