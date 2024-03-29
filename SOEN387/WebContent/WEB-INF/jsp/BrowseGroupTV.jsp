<%@include file="../include/header.jsp" %>

	<div id="container">
	
	 	<c:choose>
	 		<c:when test="${!empty notice}">
	        	<h2>${notice}</h2>
	        </c:when>
	        <c:otherwise>
				<h2>Browse Group</h2>
	        </c:otherwise>                                 
	    </c:choose>
	    
	    <ul>
			<c:if test="${!empty currentUser.group.id}">		
				<li><a href="${thePath}view_group/${currentUser.group.id}">My Group</a></li>
			</c:if>	
			<li><a href="${thePath}create_group">Create Group</a></li>
		</ul>
	    
	    <div id="display">
			<table>
				<tr>
					<td>Id</td>
					<td>Name </td>
					<td>Description</td>
					<td></td>
					<!--td></td-->		
				</tr>
				<c:forEach var="group" items="${groups}">
					<tr>
						<td>${group.id}</td>
						<td>${group.name}</td>
						<td>${group.description}</td>
						<!--  
						<td><a href="front?command=app.dispatcher.ViewGroupDispatcher&group_id=${group.id}">View</a></td>						
						-->						
						<td><a href="${thePath}view_group/${group.id}">View</a></td>
						<!-- td><a href="front?command=app.dispatcher.EditGroupDispatcher&group_id=${group.id}">Edit</a></td-->
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</br></br>
<%@include file="../include/footer.jsp" %>