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

public class ViewGroupPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/ViewGroupTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");

		if(request.getParameter("username") == null){
			//request.setAttribute("errors", "You must be logged in to access your group");
			//request.getRequestDispatcher("/jsp/LogInTV.jsp").forward(request, response);
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}else {
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		
	}

}