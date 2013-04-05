package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.ViewGroupCommand;


public class ViewGroupDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		try
		{
			new ViewGroupCommand(myHelper).process();
			forward("/WEB-INF/jsp/Home.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}

	}

	

}
