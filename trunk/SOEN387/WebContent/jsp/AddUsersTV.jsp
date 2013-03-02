<%@include file="../include/header.jsp" %>

	<h1> ADD USERS </h1>
		
	<div id="container">
		<form action="AddUsers" method="POST" target="_self" enctype="multipart/form-data">
			<% if(request.getAttribute("error") != null){
				out.print("<p id=\"error\">"+request.getAttribute("error")+"</p>");
			}%>
			<% if(request.getAttribute("info") != null){
				out.print("<p id=\"info\">"+request.getAttribute("info")+"</p>");
			}%>
			<p> Please specify a file of users to upload:<br>
				<input type="file" name="datafile" size="40">
			</p>
			<input type="submit" value="upload" /> </br>
		</form>
	</div>
	
<%@include file="../include/footer.jsp" %>