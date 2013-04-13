package app.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import dom.command.EditGroupCommand;


public class EditGroupDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		try
		{				
			new EditGroupCommand(myHelper).process();
			forward("/WEB-INF/jsp/EditGroupTV.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}
}