<%@include file="../include/header.jsp" %>

<h2>Create Group</h2>

<div id="container">
	<form action="front?command=app.dispatcher.CreateGroupDispatcher" method="POST"> 
		Group Name: <input type="text" name="name" value="" /> <br />
		Group Description: <input type="text" name="description" value="" /> <br />
		<input type="submit" value="Create Group" />
	</form>
	</br>
	<a href="front?command=app.dispatcher.BrowseGroupDispatcher">Back</a>
</div>
<%@include file="../include/footer.jsp" %>