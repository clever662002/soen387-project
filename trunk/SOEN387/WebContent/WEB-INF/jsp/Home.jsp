<%@include file="../include/header.jsp" %>

<h2>Home Page</h2>

 <div>
 	<c:choose>
 		<c:when test="${requestScope['template_view']!=null}">
        	<jsp:include page="${requestScope['template_view']}" />
        </c:when>
        <c:otherwise>
            <div>do nothing<div>
        </c:otherwise>                                 
    </c:choose>

</div>

<%@include file="../include/footer.jsp" %>