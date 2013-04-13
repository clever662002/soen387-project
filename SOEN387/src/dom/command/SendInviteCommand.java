package dom.command;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;
import dom.model.invite.mappers.InviteMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class SendInviteCommand extends Command {

	public SendInviteCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
		
		int userId = helper.getInt("user_id");
		int groupId = helper.getInt("group_id");

		//TODO check this 
		User currentUser = (User)helper.getSessionAttribute("currentUser");
		
		User userToInvite = null; 
		
		try{
			//Get the user who is sending the invite
			 userToInvite = UserMapper.find(userId);
		}
		catch(Exception ex){
			throw new CommandException(ex);
		}
		
		if(userToInvite == null){
			String msg = "That user doesn't exist.";
			helper.setRequestAttribute("error",msg);
			throw new CommandException(msg);			
		}
		
		if(userToInvite.getGroup() != null){
			if(userToInvite.getGroup().getId() != null && userToInvite.getGroup().getId() > 0){
				String msg = "That user is already in a group.";
				helper.setRequestAttribute("error",msg);
				throw new CommandException(msg);
			}
		}
		
		if(currentUser.getGroup().getId() != groupId){
			//TODO check what notifications do...
			String msg = "You can only invite someone to a group that you are a member of.";
			helper.setRequestAttribute("error",msg);
			throw new CommandException(msg);
		}
		
		//TODO check for group in identity map
		Group group = GroupMapper.find(groupId);
		
		if(group == null){
			String msg = "The group with id " + groupId + " group doesn't exist.";
			helper.setRequestAttribute("error", msg);
			throw new CommandException(msg);
		}
	
		boolean sent = true;
		
		try{
			InviteMapper.insert(userId,groupId);
		}
		catch(MapperException ex){
			helper.setRequestAttribute("error",ex.getMessage());
			sent = false;
		}
		
		if(sent){
			getNotifications().add("Invite to group '" + group.getName() + "' has been sent.");
		}
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
