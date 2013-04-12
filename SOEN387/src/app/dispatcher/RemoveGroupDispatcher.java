package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import dom.command.BrowseGroupCommand;
import dom.command.RemoveGroupCommand;

public class RemoveGroupDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			new RemoveGroupCommand(myHelper).process();
			forward("/WEB-INF/jsp/Home.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}

}
