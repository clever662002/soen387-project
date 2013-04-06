package dom.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.mappers.GroupMapper;

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
		if (sGroupName != null && !sGroupName.equals(""))
		{
			// insert action + prepare data for ViewGroupTV
			try {
				helper.setRequestAttribute("group", GroupMapper.insert(sGroupName,sGroupDesc));
				helper.setRequestAttribute("template_view","/WEB-INF/jsp/MyGroupTV.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}

}
