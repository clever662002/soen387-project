<%@include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Group</title>
</head>
<body>
<h2>Create Group</h2>

<form action="front?command=app.dispatcher.CreateGroupCommandDispatcher" method="POST"> 
	Group Name: <br /> <br /><input type="text" name="name" value="" /> <br />
	Group Description: <input type="text" name="description" value="" /> <br />
	<input type="submit" value="Submit" />
</form>
</body>	
<%@include file="../include/footer.jsp" %>
</html>