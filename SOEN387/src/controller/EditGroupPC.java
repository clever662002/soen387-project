package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;
import model.Group;

public class EditGroupPC extends BaseHttpServlet{
	private static final long serialVersionUID = 1L;

	private String VIEW_NAME = "";
	
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
			
			// when EditGroupTV call to update
			if (sGroupNameNew != null && !sGroupNameNew.equals(""))			
			{				
				// prepare data to load page
				Group g = GroupMapper.find(Integer.parseInt(sGroupID));
				g.setName(sGroupNameNew);
				g.setDescription(sGroupDescNew);
				g.setVersion(Integer.parseInt(sGroupVersionNew));
				request.setAttribute("group", GroupMapper.update(g));
				request.setAttribute("warning", "Edited successully.");
				
				VIEW_NAME = "/jsp/ViewGroupTV.jsp";
			}
			else
			{			
				request.setAttribute("group", GroupMapper.find(Integer.parseInt(sGroupID)));
				VIEW_NAME = "/jsp/EditGroupTV.jsp";
			}
			
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
