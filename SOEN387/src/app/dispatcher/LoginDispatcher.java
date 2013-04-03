package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import controller.BrowseGroupPC;

import dom.command.LoginCommand;

public class LoginDispatcher  extends Dispatcher  {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			new LoginCommand(myHelper).execute();
			forward("/WEB-INF/jsp/BrowseGroupTV.jsp");
		}
		catch(Exception e){
			forward("/WEB-INF/jsp/LogInTV.jsp");
		}
	}
}
