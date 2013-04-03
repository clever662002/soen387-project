package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dom.model.invite.Invite;
import dom.model.invite.mappers.InviteMapper;
import dom.model.invite.tdg.InviteTDG;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

@Deprecated
public class AcceptInvitePC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/WEB-INF/jsp/ViewInviteTV.jsp";
	private static final String VIEW_NAME_LOGIN = "/WEB-INF/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("user_id");
		
		if(id == null){
			request.setAttribute("error", "You need to login");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
		else{
			//TODO implement accept invite
			
			request.setAttribute("info", "Invite Accepted");
			
			int userId = Integer.parseInt(request.getSession().getAttribute("user_id")+"");
			int groupId = Integer.parseInt(request.getParameter("group_id")+"");
			
			User user = UserMapper.find(userId);
			
			if(user.getGroup() != null){
				//The user is already in a group.
			}
			
			//DELETE ALL THE INVITES
			UserMapper.deleteInvites(userId);
			
			//TODO dont reload everything again
			request.setAttribute("invites",UserMapper.findInvites(userId));
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
	}

}