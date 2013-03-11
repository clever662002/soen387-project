package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;


/**
 * Servlet implementation class BrowseGroup
 */
@WebServlet("/BrowseGroup")
public class BrowseGroup extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("groups", GroupMapper.findAll());
			String content = "/WEB-INF/jsp/BrowseGroup.jsp";
			request.getRequestDispatcher(content).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
       
   
}
