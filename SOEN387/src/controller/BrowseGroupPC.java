package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.GroupLayout.Group;

import mapper.GroupMapper;
import model.Group;

import java.util.List;
import java.util.Vector;

public class BrowseGroupPC extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_NAME = "/WEB-INF/jsp/BrowseGroupTV.jsp";
	
    public BrowseGroupPC() {
        super();
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			request.setAttribute("group", GroupMapper.findAll());
								
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}