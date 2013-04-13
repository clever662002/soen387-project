package dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.GroupProxy;
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
//			long userId = Long.parseLong(helper.getRequestAttribute("currentUser").toString());
//			User user = UserMapper.find(userId);
//			Group gp = (Group)user.getGroup();
//			Long groupId = gp.getId();
//			Group gr = GroupMapper.find(groupId);			
//			String sGroupID = helper.getString("group_id:");
//			helper.setRequestAttribute("group",gr);
//			helper.setRequestAttribute("groupId", sGroupID);
			User user= (User)helper.getSessionAttribute("currentUser");
			GroupProxy myGroup = (GroupProxy)user.getGroup();
			Long myGroupId = myGroup.getId();
			
			// change to permalink
			//long groupId = helper.getLong("group_id");
			String strGroupId = (String)helper.getAttribute("group_id");
			long groupId = Long.parseLong(strGroupId);			
			
			Group group = GroupMapper.find(groupId);
			//helper.setRequestAttribute("group",group);	
			helper.setSessionAttribute("group",group);	
//			if(groupId == myGroupId) {
//				helper.setRequestAttribute("mygroup","true");
//				helper.setRequestAttribute("group",group);				
//			}else {
//				helper.setRequestAttribute("mygroup","false");
//				helper.setRequestAttribute("group",group);
//
//			}
//			
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
