package dom.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.Group;
import dom.model.group.mappers.GroupMapper;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class CreateGroupCommand extends Command{

	public CreateGroupCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		
		//helper.setRequestAttribute("template_view","/WEB-INF/jsp/CreateGroupTV.jsp");
		String sGroupName = helper.getString("name");
		String sGroupDesc = helper.getString("description");
		User user = (User)helper.getSessionAttribute("currentUser");
		if (sGroupName != null && !sGroupName.equals(""))
		{
			// insert action + prepare data for ViewGroupTV
			try {
				if(user.getGroup() != null && user.getGroup().getId() > 0){
					String msg = "You are already in a group and cannot create one.";
					helper.setRequestAttribute("error",msg);
					throw new CommandException(msg);
				}
				
				if(GroupMapper.find(sGroupName) == null){
					//Create the group
					Group newGroup = GroupMapper.insert(new Group(sGroupName,sGroupDesc));
					helper.setRequestAttribute("group", newGroup);
					//Add the user to the group
					try{
						GroupMapper.addMember(user.getId(),newGroup.getId());
					}
					catch(MapperException ex){
						throw new CommandException(ex.getMessage());
					}
					// Delete all the users pending invites;
					UserMapper.deleteInvites(Integer.parseInt(user.getId()+""));
					
					helper.setRequestAttribute("template_view","/WEB-INF/jsp/MyGroupTV.jsp");
				}
				else{
					helper.setRequestAttribute("error", "That group name is already taken");
					throw new CommandException("That group name is already taken.");
				}
				String notice = sGroupName + " has been created successfully.";
				helper.setRequestAttribute("notice", notice);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//helper.setRequestAttribute("error", value)
				e.printStackTrace();
			}
		}
		else{
			helper.setRequestAttribute("error", "A group name is required.");
			throw new CommandException("A group name is required.");
		}

	}
	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}

}
