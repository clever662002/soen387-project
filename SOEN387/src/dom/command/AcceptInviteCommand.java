package dom.command;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.mappers.GroupMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class AcceptInviteCommand extends Command {

	public AcceptInviteCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
		
		// change to permalink - not sure what Alex meants. Try to fix (based on DeclineInviteCommand)!!!
		/*
		int userId = helper.getInt("user_id");
		int groupId = helper.getInt("group_id");
		*/
		String strUserId  = ((User)helper.getSessionAttribute("currentUser")).getId() +"";
		String strGroupId = (String)helper.getAttribute("group_id");
		int userId  = Integer.parseInt(strUserId);
		int groupId = Integer.parseInt(strGroupId);
				
		User user = null;
		try{
			//Get the user who is sending the invite
			user = UserMapper.find(userId);
		}
		catch(Exception ex){
			throw new CommandException(ex);
		}
		
		//TODO if user null do something.
		
		if(user.getGroup() != null || user.getGroup().getId() <= 0){
			//The user is already in a group.
		}
		
		//DELETE ALL THE INVITES
		UserMapper.deleteInvites(userId);
		
		//TODO dont reload everything again
		helper.setSessionAttribute("invites",UserMapper.findInvites(userId));
		
		//TODO add user to group.
		try {
			GroupMapper.addMember(userId, groupId);
		}
		catch(MapperException ex){
			throw new CommandException(ex.getMessage());
		}
		
		// Get the group
		helper.setSessionAttribute("group",GroupMapper.find(groupId));
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}

	
	
}
