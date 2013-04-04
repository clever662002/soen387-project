<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<h1> THERE WAS AN ERROR ! </h1>

<c:if test="${!empty errorMessage}">
	<p id="error"> ${errorMessage} </p>
</c:if>