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
			<!-- change to permalink
			<li><a href="front?command=app.dispatcher.CreateGroupFormDispatcher">Create Group</a></li>
			-->			
			<li><a href="<%out.print(strAbsolutePath + "create_group"); %>">Create Group</a></li>
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
						<td><a href="<%out.print(strAbsolutePath + "view_group/");%>${group.id}">View</a></td>
						<!-- td><a href="front?command=app.dispatcher.EditGroupDispatcher&group_id=${group.id}">Edit</a></td-->
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</br></br>
<%@include file="../include/footer.jsp" %>