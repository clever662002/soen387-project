package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;
import model.Group;

public class RemoveGroupPC extends BaseHttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/BrowseGroupTV.jsp";
	
	public RemoveGroupPC() {
        super();
    }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String sGroupID   = request.getParameter("group_id");
			
			// delete requested group
			Group g = GroupMapper.find(Integer.parseInt(sGroupID));
			GroupMapper.delete(g);
			
			// prepare data for BrowseGroupTV
			request.setAttribute("group", GroupMapper.findAll());
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
