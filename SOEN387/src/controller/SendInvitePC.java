package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;
import dom.model.invite.Invite;
import dom.model.invite.mappers.InviteMapper;
import dom.model.invite.tdg.InviteTDG;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

import utils.SecurityUtil;



public class SendInvitePC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/WEB-INF/jsp/SendInviteTV.jsp";
	private static final String VIEW_NAME_LOGIN = "/WEB-INF/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(SecurityUtil.isAuthenticated(request)){
			try{
				if(request.getParameter("user_id") == null || request.getParameter("user_id") == "" || request.getParameter("group_id") == null || request.getParameter("group_id") == ""){
					request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					return;
				}
				
				int userId = Integer.parseInt(request.getParameter("user_id"));
				int groupId = Integer.parseInt(request.getParameter("group_id"));
			
				//Should I not use the user_id directly from the session?
				int id = Integer.parseInt(request.getSession().getAttribute("user_id")+"");
				//Get the user who is sending the invite
				User loggedUser = UserMapper.find(id); 
				
				if(loggedUser == null){
					request.setAttribute("error", "You don't exist. Weird.");
					request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					return;
				}
				
				//Get the user who is sending the invite
				User userToInvite = UserMapper.find(userId); 
				
				if(userToInvite == null){
					request.setAttribute("error", "The user you are trying to invite does not exist.");
					request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					return;
				}
				
				if(loggedUser.getGroup().getId() != groupId){
					request.setAttribute("error", "You can only invite someone to a group that you are a member of.");
					request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					return;
				}
				
				//TODO check for group in identity map
				Group group = GroupMapper.find(groupId);
				
				if(group == null){
					request.setAttribute("error", "That group doesn't exist.");
					request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					return;
				}
			
				//ADD user to group
				InviteMapper.insert(userId,groupId);
				
				request.setAttribute("info", "invite sent");
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}
			catch(Exception ex){
				request.setAttribute("error", "There was a problem sending an invite");
				
			}
		}
		else{
			request.setAttribute("error", "You need to login");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
	}
	
}