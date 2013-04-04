package dom.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;

import java.util.List;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

import dom.model.group.mappers.GroupMapper;


public class BrowseGroupCommand extends Command{
	public BrowseGroupCommand(Helper helper)
	{
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
		helper.setSessionAttribute("group", GroupMapper.findAll());
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
