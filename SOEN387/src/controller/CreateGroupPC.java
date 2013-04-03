package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dom.model.group.mappers.GroupMapper;


public class CreateGroupPC extends BaseHttpServlet 
{
	
	private static final long serialVersionUID = 1L;

	private static String VIEW_NAME = "/WEB-INF/jsp/CreateGroupTV.jsp";

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String sGroupName = request.getParameter("name");
			String sGroupDesc = request.getParameter("description");
			
			if (sGroupName != null && !sGroupName.equals(""))
			{
				// insert action + prepare data for ViewGroupTV
				request.setAttribute("group", GroupMapper.insert(sGroupName,sGroupDesc));
			}
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
