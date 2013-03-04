package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SecurityUtil;

import mapper.UserMapper;
import model.Group;
import model.IGroup;
import model.User;

public class LogInPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME_LOGIN = "/jsp/LogInTV.jsp";
	private static final String VIEW_NAME_GROUP = "/jsp/ViewGroupTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check the see if the user is already authenticated
		if(SecurityUtil.isAuthenticated(request)){
			request.getRequestDispatcher(VIEW_NAME_GROUP).forward(request, response);
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// do some basic validation on username and password
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			//request.setAttribute("errors", "invalid username or password");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
		else{
			
			//TODO check the identity map for the user
			User user = UserMapper.find(username);
			
			if(user == null){
				//Redirect to login page (user does not exist)
				System.out.println("Did not find a user with username: " + username);
				request.setAttribute("error", "invalid username or password.");
				request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
			}
			else{ 
				System.out.println("found user [" + user.toString() + "]");

				if(user.getGroup() != null){
					System.out.println("User is in group [" + user.getGroup().getId() + "]");
				}
				
				request.getSession().setAttribute("username",user.getUsername());
				request.getSession().setAttribute("user_id",user.getId()+"");
				request.getRequestDispatcher("/jsp/Index.jsp").forward(request, response); //somewhere else!
			}
		}
	}

}