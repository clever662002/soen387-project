package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;
import model.Group;

public class EditGroupPC extends BaseHttpServlet{
	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME = "/jsp/EditGroupTV.jsp";
	
	 public EditGroupPC() {
	        super();
	    }
	
	@Override
	/*
	 * This function is called either to 
	 * . load page 
	 * . reload page after submit button 'edit'
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String sGroupID   		= request.getParameter("group_id");
			String sGroupNameNew 	= request.getParameter("name");
			String sGroupDescNew 	= request.getParameter("description");
			String sGroupVersionNew = request.getParameter("version");
						
			// prepare data to load page
			Group g = GroupMapper.find(Integer.parseInt(sGroupID));
			
			// when EditGroupTV call to update
			if (sGroupNameNew != null && !sGroupNameNew.equals(""))			
			{
				g.setName(sGroupNameNew);
				g.setDescription(sGroupDescNew);
				g.setVersion(Integer.parseInt(sGroupVersionNew));
				request.setAttribute("group", GroupMapper.update(g));
				request.setAttribute("warning", "Edited successully.");
			}
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
