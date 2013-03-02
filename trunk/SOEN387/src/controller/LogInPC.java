package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.UserMapper;
import model.User;

public class LogInPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// do some basic validation on username and password
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			request.setAttribute("errors", "Invalid username or password");
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		else{
			
			User user = UserMapper.find(username);
			
			if(user == null){
				//Redirect to login page (user does not exist)
				System.out.println("Did not find a user with username: " + username);
				request.setAttribute("errors", "Invalid username or password.");
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}
			else{
				System.out.println("found user [" + user.toString() + "]");
				request.getSession().setAttribute("username",username);
				request.getRequestDispatcher("/jsp/Index.jsp").forward(request, response); //somewhere else!
			}
		}
	}

}