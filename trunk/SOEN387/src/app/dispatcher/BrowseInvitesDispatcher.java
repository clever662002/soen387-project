package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.BrowseInvitesCommand;

public class BrowseInvitesDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			new BrowseInvitesCommand(myHelper).execute();
			forward("/WEB-INF/jsp/ViewInviteTV.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
			forward("/WEB-INF/jsp/ViewInviteTV.jsp");
		}
	}
}
