package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;

public class CreateGroupPC extends BaseHttpServlet 
{
	
	private static final long serialVersionUID = 1L;

	private String VIEW_NAME = "";
	
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
						
				VIEW_NAME = "/jsp/ViewGroupTV.jsp";
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}
			else
			{
				VIEW_NAME = "/jsp/CreateGroupTV.jsp";
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
