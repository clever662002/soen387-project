<%@include file="../include/header.jsp" %>
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
		<p>You are in any groups!</p>
	</c:if>
	<c:if test="${!empty group}">
		<table>
			<tr>
				<td>Id</td>
				<td>Group</td>
				<td>Description</td>
			</tr>				
			<c:forEach items="${group}" var="invite">
			<tr>
				<td>${group.id}</td>
				<td>${group.name}</td>
				<td>${group.description}</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</body>	

<%@include file="../include/footer.jsp" %>
</html>