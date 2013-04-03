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
import dom.model.group.GroupProxy;
import dom.model.group.mappers.GroupMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;



public class ViewGroupPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/WEB-INF/jsp/ViewGroupTV.jsp";
	private static final String NO_GROUP = "/WEB-INF/jsp/NoGroupTV.jsp";
	
		
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int userId = Integer.parseInt(request.getSession().getAttribute("user_id")+"");
			User user = UserMapper.find(userId);
			
			GroupProxy gp = user.getGroup();
			int sGroupId = gp.getId();
			
			Group gr = GroupMapper.find(sGroupId);			
			String sGroupID = request.getParameter("group_id:");
			// if the user doesn't register for a group right now.
			if(gr == null){
				request.getRequestDispatcher(NO_GROUP).forward(request, response);
			}else {
				request.setAttribute("group", gr);
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}