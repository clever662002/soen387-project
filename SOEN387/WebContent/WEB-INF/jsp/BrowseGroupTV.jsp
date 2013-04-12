<%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Group</title>
</head>

<body>
 	<c:choose>
 		<c:when test="${!empty notice}">
        	<h2>${notice}</h2>>
        </c:when>
        <c:otherwise>
			<h2>Browse Group</h2>
        </c:otherwise>                                 
    </c:choose>
	



<table>
	<tr>
		<td>group id</td>
		<td>group name</td>
		<td>group description</td>
		<td></td>		
	</tr>

	<c:forEach var="group" items="${groups}">
		<tr>
			<!-- 
			<td><a href="front?command=app.dispatcher.ViewGroupDispatcher&group_id=${group.id}"><c:out value="${group.id}" /></a></td>
			-->
			<td><a href="view_group/${group.id}"><c:out value="${group.id}" /></a></td>
			
			<td><c:out value="${group.name}" /></td>
			<td><c:out value="${group.description}" /></td>
		</tr>
	</c:forEach>
	
	<tr>
	<td><a href="front?command=app.dispatcher.CreateGroupFormDispatcher">Create New Group</a><br/></td>
	</tr>
	
 </table>
</body>
</html>