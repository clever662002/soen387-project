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

public class EditGroupCommand extends Command{

	public EditGroupCommand(Helper helper) {
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
		{	/*
			User user= (User)helper.getSessionAttribute("currentUser");
			GroupProxy myGroup = (GroupProxy)user.getGroup();
			Long myGroupId = myGroup.getId();
			long groupId = helper.getLong("group_id");
			Group group = GroupMapper.find(groupId);			
			*/
			
			//long groupId 			= helper.getLong("group_id");
			String sGroupID = (String)helper.getAttribute("group_id");
			long groupId = Long.parseLong(sGroupID);	
			
			String sGroupNameNew 	= helper.getString("name");			
			String sGroupDescNew 	= helper.getString("description");
			String sGroupVersionNew = helper.getString("version");
						
			if (sGroupNameNew != null && !sGroupNameNew.equals(""))			
			{	
				// prepare data to load page				
				Group g = GroupMapper.find(groupId);				
				g.setName(sGroupNameNew);
				g.setDescription(sGroupDescNew);
				g.setVersion(Long.parseLong(sGroupVersionNew));
								
				helper.setRequestAttribute("group", GroupMapper.update(g));
				helper.setRequestAttribute("warning", "Edited successully.");
				//System.out.println(g.getDescription());
				helper.setRequestAttribute("template_view","/WEB-INF/jsp/MyGroupTV.jsp");
			}
			else
			{					
				helper.setRequestAttribute("group", GroupMapper.find(groupId));
				helper.setRequestAttribute("template_view","/WEB-INF/jsp/EditGroupTV.jsp");
			}			
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
