<%@include file="../include/header.jsp" %>
	
<h1> THERE WAS AN ERROR ! </h1>

<c:if test="${!empty errorMessage}">
	<p id="error"> ${errorMessage} </p>
</c:if> 

<%@include file="../include/footer.jsp" %>