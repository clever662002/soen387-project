package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.GroupMapper;

public class BrowseGroupPC extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_NAME = "/jsp/BrowseGroupTV.jsp";
	
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