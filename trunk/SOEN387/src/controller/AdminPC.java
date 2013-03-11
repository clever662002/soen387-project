package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOutPC
 */
public class AdminPC extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/jsp/Admin.jsp";
	@Override
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request,response);
	}
       
}
