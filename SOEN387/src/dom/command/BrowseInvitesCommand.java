package dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.invite.Invite;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class BrowseInvitesCommand extends Command {

	public BrowseInvitesCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
		
		String id = helper.getString("user_id");
		if(id == null){
			//throw new CommandException("User id not set.");
			id = ((User)helper.getSessionAttribute("currentUser")).getId()+"";
		}
		List<Invite> invites = UserMapper.findInvites(Integer.parseInt(id));
		helper.setSessionAttribute("invites", invites);
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
