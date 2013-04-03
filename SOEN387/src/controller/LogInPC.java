package controller;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.resources.jndi.Handler;

import dom.model.group.Group;
import dom.model.group.IGroup;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

import utils.SecurityUtil;

@Deprecated
public class LogInPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_NAME_LOGIN = "/WEB-INF/jsp/LogInTV.jsp";
	private static final String VIEW_NAME_INDEX = "/WEB-INF/jsp/Index.jsp";

	private static final String VIEW_NAME_GROUP = "/WEB-INF/jsp/ViewGroupTV.jsp";

	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check the see if the user is already authenticated
		if(SecurityUtil.isAuthenticated(request)){
			request.getRequestDispatcher(VIEW_NAME_GROUP).forward(request, response);
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// do some basic validation on username and password
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			//request.setAttribute("errors", "invalid username or password");
			request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
		}
		else{
			
			//TODO check the identity map for the user
			User user = UserMapper.find(username);
			
			if(user == null || !password.equals(user.getPassword())){
				//Redirect to login page (user does not exist)
				request.setAttribute("error", "invalid username or password.");
				request.getRequestDispatcher(VIEW_NAME_LOGIN).forward(request, response);
			}
			else{ 
				//TODO Regenerate the session
				//HashMap<String, Object> attributes = new HashMap<String, Object>();
				//Enumeration<String> attributesNames = request.getSession().getAttributeNames();
				//HttpSession newSession = request.getSession(true);
				//HttpCookie cookie = new HttpCookie("sessionId",request.getSession().getId());
				//cookie.setMaxAge(60*60*60*60*60);
				
				System.out.println("found user [" + user.toString() + "]");

				if(user.getGroup() != null){
					System.out.println("User is in group [" + user.getGroup().getId() + "]");
				}

				request.getSession().setAttribute("username",user.getUsername());
				request.getSession().setAttribute("user_id",user.getId()+"");
				
				if(UserMapper.isAdmin(user.getId())){
					request.getSession().setAttribute("admin", "true");
				}
				
				request.getRequestDispatcher(VIEW_NAME_GROUP).forward(request, response); //somewhere else!
			}
		}
	}

}