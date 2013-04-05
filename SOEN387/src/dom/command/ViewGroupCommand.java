package dom.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class ViewGroupCommand extends Command{

	public ViewGroupCommand(Helper helper) {
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
			int userId = Integer.parseInt(helper.getRequestAttribute("user_id").toString());
			User user = UserMapper.find(userId);
			
			Group gp = (Group)user.getGroup();
			Long sGroupId = gp.getId();
			
			Group gr = GroupMapper.find(sGroupId);			
			String sGroupID = helper.getString("group_id:");
			helper.setRequestAttribute("group",gr);
			helper.setRequestAttribute("groupId", sGroupID);
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
