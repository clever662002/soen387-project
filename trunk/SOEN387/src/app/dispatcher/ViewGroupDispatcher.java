package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.CreateGroupCommand;
import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class ViewGroupDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		try
		{
			new CreateGroupCommand(myHelper).process();
			forward("/WEB-INF/jsp/ViewGroupTV.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}

	}

	

}
