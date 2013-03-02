<%@include file="../include/header.jsp" %>

	<h1> LOG IN </h1>
	<div id="container">
		<p id="error"></p>
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