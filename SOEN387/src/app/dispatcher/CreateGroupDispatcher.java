package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import dom.command.CreateGroupCommand;

public class CreateGroupDispatcher extends Dispatcher{
	@Override
	public void execute() throws ServletException, IOException
	{
		try {
			new CreateGroupCommand(myHelper).process();
			//forward("/WEB-INF/jsp/ViewGroupTV.jsp");
			
			String path = this.myRequest.getContextPath() + this.myRequest.getServletPath() + "/" + "view_group";			
			this.myResponse.sendRedirect(path);
		}
		catch(CommandException ex){
			forward("/WEB-INF/jsp/CreateGroupTV.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}
}
