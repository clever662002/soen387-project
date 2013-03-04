<%@page import="java.util.List"%>
<%@page import="model.Invite"%>
<%@include file="../include/header.jsp" %>

	<h2> INVITES </h2>
	
	<ul>	
		<li><a href="SendInvite">Send Invite</a></li>
	</ul>	
		
	<%
	if(request.getAttribute("info") != null){
		out.print("<p id=\"info\">"+request.getAttribute("info").toString()+"</p>");
	}
	if(request.getAttribute("error") != null){
		out.print("<p id=\"error\">"+request.getAttribute("error").toString()+"</p>");
	}
	
	List<Invite> invites = (List<Invite>)request.getAttribute("invites");
	if(invites != null && invites.size() > 0){
		out.print("<table><tr><td>Id</td><td>Group</td><td>Description</td><td></td><td></td></tr>");
		for(Invite invite : invites){
			out.print("<tr>");
			out.print("<td>"+invite.getId()+"</td>");
			out.print("<td>"+invite.getGroup().getName()+"</td>");
			out.print("<td>"+invite.getGroup().getDescription()+"</td>");
			out.print("<td><a href=\"AcceptInvite?group_id="+invite.getGroup().getId()+"\">Accept</a></td>");
			out.print("<td><a href=\"DeclineInvite?group_id="+invite.getGroup().getId()+"\">Decline</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
	else{
		out.print("<p> You have no invites. </p>");
	}
	
	%>
	
<%@include file="../include/footer.jsp" %>