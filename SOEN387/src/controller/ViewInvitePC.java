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
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

import utils.SecurityUtil;


public class ViewInvitePC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/WEB-INF/jsp/ViewInviteTV.jsp";
	private static final String VIEW_NAME_LOGIN = "/WEB-INF/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");

		String id = (String) request.getSession().getAttribute("user_id");
		
		if(!SecurityUtil.isAuthenticated(request)){
			request.setAttribute("error", "You need to login");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
		else{
			
			List<Invite> invites = UserMapper.findInvites(Integer.parseInt(id));
			request.setAttribute("invites", invites);
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
	}

}