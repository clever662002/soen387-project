package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.AddUsersCommand;
import dom.command.BrowseInvitesCommand;
import dom.command.DeclineInviteCommand;

public class DeclineInviteDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			new DeclineInviteCommand(myHelper).execute();
			forward("/WEB-INF/jsp/ViewInviteTV.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
			forward("/WEB-INF/jsp/ViewInviteTV.jsp");
		}
	}
	
}
