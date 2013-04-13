<%@include file="../include/header.jsp" %>

<h2>Edit Group</h2>

<form action="front?command=app.dispatcher.EditGroupDispatcher" method="POST">

	<div id="container">
		<c:if test="${!empty warning}">
			<p id="error"> ${warning} </p>
		</c:if>
		<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
		</c:if>
		<c:if test="${!empty group}">	
			<div id="display">
				<table>
					<tr><!--td>Id</td-->
						<td>Id</td>
						<td>Name</td>
						<td>Description</td>
						<td>Version</td>
						<td></td>
					</tr>
					<tr>
						<td>${group.id}</td>
						<td><input type="text" name="name" id="name" value="${group.name}" /></td>
						<td><input type="text" name="description" id="description" value="${group.description}" /></td>
						<td>${group.version}</td>						
						<td><input type="submit" value="Update" /></td>
					</tr>
				</table>
				<input type="hidden" name="version" id="version" value="${group.version}" />
				<input type="hidden" name="group_id" id="group_id" value="${group.id}">
			</div>
		</div>
	</c:if>
	</br>
	<a href="front?command=app.dispatcher.BrowseGroupDispatcher">Back</a>
</form>
<%@include file="../include/footer.jsp" %>