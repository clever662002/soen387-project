package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import dom.command.CreateGroupFormCommand;

public class CreateGroupFormDispatcher extends Dispatcher{
	
	public void execute() throws ServletException, IOException {
		try
		{
			new CreateGroupFormCommand(myHelper).process();
			forward("/WEB-INF/jsp/Home.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}

}
