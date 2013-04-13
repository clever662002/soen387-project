<%@include file="../include/header.jsp" %>

<h2>Create Group</h2>

<div id="container">

	<div>

		<c:if test="${!empty error}">
			<p id="error"> ${error} </p>
		</c:if>
		<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
		</c:if> 
		
		<form action="front?command=app.dispatcher.CreateGroupDispatcher" method="POST">
		 
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" value="" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create Group" /></td>
			</tr>
		</table>	
		</form>
		</br>
		<a href="front?command=app.dispatcher.BrowseGroupDispatcher">Back</a>
	</div>
</div>
<%@include file="../include/footer.jsp" %>