package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.model.user.GuestUser;

public class LogoutDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {

		try{
			myRequest.getSession().invalidate();
			//TODO change for guest user
			myHelper.setSessionAttribute("currentUser", new GuestUser());
			
			// change to permalink
			//forward("/WEB-INF/jsp/LogInTV.jsp");
			String path = this.myRequest.getContextPath() + this.myRequest.getServletPath();			
			this.myResponse.sendRedirect(path);
			
		}
		catch(Exception e){
			forward("/WEB-INF/jsp/LogInTV.jsp");
		}
	}

	
}
