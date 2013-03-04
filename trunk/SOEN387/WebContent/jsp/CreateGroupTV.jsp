<%@include file="../include/header.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Person</title>
</head>
<body>
<h2>View Person</h2>

<form action="CreateGroup" method="POST" target="_self" enctype="multipart/form-data">
		 
	Group Name: 		<input type="text" name="name" 			value="" /><br/>
	Group Description: 	<input type="text" name="description" 	value="" /> <br />
	Group Version: 		1 <br />

	<input type="submit" value="Submit" /> </br>
</form>
</body>	

<%@include file="../include/footer.jsp" %>
</html>