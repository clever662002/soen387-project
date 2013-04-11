package dom.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;

import java.sql.SQLException;
import java.util.List;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;


public class BrowseGroupCommand extends Command{
	public BrowseGroupCommand(Helper helper)
	{
		super(helper);
	}
	
	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		//helper.setSessionAttribute("group", GroupMapper.findAll());
		List<Group> groups = null;
		try{
			groups = GroupMapper.findAll();
		}
		catch(SQLException ex){
			throw new CommandException(ex);
		}
		helper.setRequestAttribute("groups",groups);
		helper.setRequestAttribute("template_view","/WEB-INF/jsp/BrowseGroupTV.jsp");
		
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}
}
