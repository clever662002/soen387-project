package dom.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.invite.mappers.InviteMapper;
import dom.model.user.mappers.UserMapper;

public class DeclineInviteCommand extends Command {

	public DeclineInviteCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
	
		//TODO implement decline invite
		
		String userId = helper.getString("user_id");
		String groupId = helper.getString("group_id");
		
		if(groupId == null){
			throw new CommandException("Group Id is null.");
		}
		
		if(userId == null){
			throw new CommandException("User Id is null.");
		}
		
		//TODO finish this method
		
		InviteMapper.delete(Integer.parseInt(userId),Integer.parseInt(groupId));
		
		helper.setSessionAttribute("invites",UserMapper.findInvites(Integer.parseInt(userId)));
		getNotifications().add("Invite Declined.");
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
