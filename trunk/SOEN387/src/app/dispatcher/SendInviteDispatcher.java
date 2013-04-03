package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.AcceptInviteCommand;
import dom.command.AddUsersCommand;
import dom.command.BrowseInvitesCommand;
import dom.command.SendInviteCommand;

public class SendInviteDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			new SendInviteCommand(myHelper).execute();
			forward("/WEB-INF/jsp/SendInviteTV.jsp");
		}
		catch(Exception e){
			forward("/WEB-INF/jsp/SendInviteTV.jsp");
		}
	}
}
