package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import dom.command.BrowseGroupCommand;

public class BrowseGroupDispatcher extends Dispatcher{	
	@Override
	public void execute() throws ServletException, IOException
	{
		try
		{
			new BrowseGroupCommand(myHelper).process();
			//forward("/WEB-INF/jsp/Home.jsp");
			forward("/WEB-INF/jsp/BrowseGroupTV.jsp"); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}
	 
}
