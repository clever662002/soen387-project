<%@include file="../include/header.jsp" %>

<h2>Create Group</h2>

<div id="container">
	<c:if test="${!empty notice}">
			<p id="notice"> ${notice} </p>
	</c:if>
	<c:if test="${!empty error}">
			<p id="error"> ${error} </p>
	</c:if>
	<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
	</c:if> 
	
	<!-- change to permalink 
	<form action="front?command=app.dispatcher.CreateGroupDispatcher" method="POST">
	--> 
	<form action="create_group" method="POST">
		Group Name: <input type="text" name="name" value="" /> <br />
		Group Description: <input type="text" name="description" value="" /> <br />
		<input type="submit" value="Create Group" />
	</form>
	<br />
	
	<!-- change to permalink 
	<a href="front?command=app.dispatcher.BrowseGroupDispatcher">Back</a>
	-->
	<a href="browse_group">Back</a>
</div>
<%@include file="../include/footer.jsp" %>