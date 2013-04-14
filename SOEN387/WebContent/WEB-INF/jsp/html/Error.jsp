<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<c:set var="thePath" value="http://${pageContext.request.serverName}:8080${pageContext.request.contextPath}/front/"/>

<h1> THERE WAS AN ERROR ! </h1>

<c:if test="${!empty errorMessage}">
	<p id="error"> ${errorMessage} </p>
</c:if>

<a href="${thePath}"> Get me out of here! </a>