<%@include file="../include/header.jsp" %>

	<h2> LOGIN </h2>
	<div id="container">
		<form action="Login" method="POST" target="_self">
			<% if(request.getAttribute("error") != null){
				out.print("<p id=\"error\">"+request.getAttribute("error")+"</p>");
			}%>
			<span>USERNAME </span> <input type="text" name="username" /> </br>
			<span>PASSWORD </span> <input type="password" name="password" /> </br>
			<input type="submit" value="GO" /> </br>
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>