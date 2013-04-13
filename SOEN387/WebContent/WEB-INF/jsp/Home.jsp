<%@include file="/WEB-INF/include/header.jsp" %>

<h2>Home Page</h2>

 <div>
 	<c:choose>
 		<c:when test="${requestScope['template_view']!=null}">
        	<jsp:include page="${requestScope['template_view']}" />
        </c:when>
        <c:otherwise>
            <div>Welcome! Click on a link to get started. <div>
        </c:otherwise>                                 
    </c:choose>

</div>

<%@include file="/WEB-INF/include/footer.jsp" %>