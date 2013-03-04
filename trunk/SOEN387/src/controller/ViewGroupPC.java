package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;
import model.Group;


public class ViewGroupPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/ViewGroupTV.jsp";
	private static final String VIEW_NAME_LOGIN = "/jsp/LogInTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String sGroupID = request.getParameter("group_id");
			
			if(sGroupID != null)
			{
				request.setAttribute("group", GroupMapper.find(Integer.parseInt(sGroupID)));
			}
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}