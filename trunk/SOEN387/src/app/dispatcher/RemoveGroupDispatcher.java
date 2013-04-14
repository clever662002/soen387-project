package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

import dom.command.RemoveGroupCommand;

public class RemoveGroupDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{			
			new RemoveGroupCommand(myHelper).process();			
			//forward("/WEB-INF/jsp/BrowseGroupTV.jsp");
			
			String path = this.myRequest.getContextPath() + this.myRequest.getServletPath() + "/" + "browse_group";			
			this.myResponse.sendRedirect(path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forward("/WEB-INF/jsp/html/Error.jsp");
		}
	}

}
