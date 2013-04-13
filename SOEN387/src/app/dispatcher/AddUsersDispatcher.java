package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.AddUsersCommand;

public class AddUsersDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try{
			new AddUsersCommand(myHelper).execute();
			forward("/WEB-INF/jsp/AddUsersTV.jsp");
		}
		catch(Exception e){
			forward("/WEB-INF/jsp/AddUsersTV.jsp");
		}
	}
	
}
