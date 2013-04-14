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
					<li><a href="${thePath}edit_group/${group.id}">Edit Group</a></li>
					<li><a href="${thePath}remove_group/${group.id}">Remove Group</a></li>
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
		<a href="${thePath}browse_group">Back</a>
	</div>
<%@include file="../include/footer.jsp" %>