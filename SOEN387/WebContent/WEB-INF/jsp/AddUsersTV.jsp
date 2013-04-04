<%@include file="../include/header.jsp" %>

	<h2> ADD USERS </h2>
		
	<ul>
		<li><a href="AddUsers">ADD USERS</a></li>
	</ul>
		
	<div id="container">
		<form action="AddUsers" method="POST" target="_self" enctype="multipart/form-data">
			
			<c:if test="${!empty error}">
				<p id="error"> ${error} </p>
			</c:if>
			<c:if test="${!empty info}">
				<p id="info"> ${info} </p>
			</c:if> 	
				
			<%
			/*
				if(request.getAttribute("error") != null){
				out.print("<p id=\"error\">"+request.getAttribute("error")+"</p>");
			}
			*/
			%>
			<% 
			/*
				if(request.getAttribute("info") != null){
				out.print("<p id=\"info\">"+request.getAttribute("info")+"</p>");
			}
			*/
			%>
			<p> Please specify a file of users to upload:<br>
				<input type="submit" value="upload" /> <input type="file" name="datafile" size="40">
				
			</p>
			
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>