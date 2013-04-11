<%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>View Person</title>
</head>
<body>
	<h2>ViewGroup</h2>
		
	<c:if test="${!empty error}">
		<p id="error"> ${error} </p>
	</c:if>
	
	<c:if test="${empty group}">
		<p>You are not in any groups!</p>
	</c:if>
	<c:if test="${!empty group}">
		<table>
			<tr>
				<td>Id :</td>
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
	</c:if>
</body>	
</html>