<%@include file="../include/header.jsp" %>
	

<%@page import="dom.model.group.Group"%>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>View Person</title>
</head>
<body>
	<h2>View Group</h2>

	<%
	Group group = (Group)request.getAttribute("group");
	%>
	<table  CELLPADDING="3" CELLSPACING="1">
		<tr>
			<td>Group ID: <%= group.getId()%></td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td>Group Name: <%= group.getName()%> </td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>		
		<td>Group Description: 	<%= group.getDescription()%> </td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
		<td>Group Version: <%= group.getVersion()%> </td>
			<td></td>
			<td></td>
		</tr>
	
	
		<tr>
			<td>
		    	<a href="EditGroup?group_id=<%= group.getId() %>&version=<%= group.getVersion() %>"> Edit Group</a>
		    </td>
		    <td>
		    	<a href="RemoveGroup?group_id=<%= group.getId()%>">Remove Group</a>
		    </td>
		    <td>
				<a href="BrowseGroup">Browse All Groups</a>
			</td>
		</tr>
	</table>

</body>	

<%@include file="../include/footer.jsp" %>
</html>