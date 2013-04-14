package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.LoginCommand;
import dom.model.user.GuestUser;

public class LoginDispatcher  extends Dispatcher  {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			if(myHelper.getSessionAttribute("currentUser") instanceof GuestUser){
				new LoginCommand(myHelper).execute();
			}
			forward("/WEB-INF/jsp/Home.jsp");
		}
		catch(Exception e){
			forward("/WEB-INF/jsp/LogInTV.jsp");
		}
	}
}
