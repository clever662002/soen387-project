<%@include file="../include/header.jsp" %>

	<h2> LOGIN </h2>
	<div id="container">
		<form action="front" method="POST" target="_self">
			<c:if test="${!empty error}">
				<p id="error"> ${error} </p>
			</c:if>
			<c:if test="${!empty info}">
				<p id="info"> ${info} </p>
			</c:if> 
			<c:if test="${!empty notifications}">
				<p id="info"> ${notifications} </p>
			</c:if> 
					
			<span>USERNAME </span> <input type="text" name="username" /> </br>
			<span>PASSWORD </span> <input type="password" name="password" /> </br>
			<input type="submit" value="GO" /> </br>
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>