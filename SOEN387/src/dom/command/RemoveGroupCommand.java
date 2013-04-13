package dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;

public class RemoveGroupCommand extends Command{

	public RemoveGroupCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		try
		{			
			//String sGroupID   = helper.getString("group_id");			
			//String sGroupID   = (String)helper.getAttribute("group_id");
			//long group_id     = Long.parseLong(sGroupID);
			long group_id 	    = helper.getLong("group_id");
			
			System.out.println("RemoveCommand: group_id=[" + group_id + "]");
			// delete requested group
			Group g = GroupMapper.find(group_id);
			String notice = g.getName() + " has been deleted";
			helper.setRequestAttribute("notice", notice);
			GroupMapper.delete(g);
			List<Group> groups = GroupMapper.findAll();
			helper.setRequestAttribute("groups",groups);
			helper.setRequestAttribute("template_view","/WEB-INF/jsp/BrowseGroupTV.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}

}
