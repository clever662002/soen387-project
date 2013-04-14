<%@include file="../include/header.jsp" %>

	<h2> ADD USERS </h2>
		
	<div id="container">
		<ul>
			<li><a href="${thePath}add_user">ADD USERS</a></li>
		</ul>

		<div id="addUserContainer">
			<div id="addUser">
				<form action="front?command=app.dispatcher.AddUsersDispatcher" method="POST" target="_self" enctype="multipart/form-data">
					<c:if test="${!empty error}">
						<p id="error"> ${error} </p>
					</c:if>
					<c:if test="${!empty notifications}">
						<p id="info"> ${notifications} </p>
					</c:if> 
	
					<p> Please specify a file of users to upload:<br>
						<input type="submit" value="Upload" /> <input type="file" name="datafile" size="40">
					</p>
				</form>
			</div>
		</div>
	</div>
<%@include file="../include/footer.jsp" %>