package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SecurityUtil;

import mapper.InviteMapper;
import mapper.UserMapper;
import model.Invite;
import model.User;

public class DeclineInvitePC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/ViewInviteTV.jsp";
	private static final String VIEW_NAME_LOGIN = "/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(!SecurityUtil.isAuthenticated(request)){
			request.setAttribute("error", "You need to login");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
		else{
			//TODO implement decline invite
			//int inviteId = Integer.parseInt(request.getAttribute("invite_id")+"");
			int userId = Integer.parseInt(request.getSession().getAttribute("user_id")+"");
			int groupId = Integer.parseInt(request.getParameter("group_id")+"");
			
			InviteMapper.delete(userId,groupId);
			
			request.setAttribute("info", "invite declined.");
			request.setAttribute("invites",UserMapper.findInvites(userId));
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
	}

}