package dom.command;

import java.sql.SQLException;


import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

import dom.model.user.mappers.UserMapper;

public class LoginCommand extends Command{

	public LoginCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {

		String username = helper.getString("username");
		String password = helper.getString("password");
		
		if(username == null || password == null){
			throw new CommandException("");
		}
		
		try{
			helper.setSessionAttribute("currentUser",UserMapper.find(username,password));
		}
		catch(SQLException ex){
			ex.printStackTrace();
			throw new CommandException(ex);
		}
		catch(MapperException ex){
			getNotifications().add("No user for that username and password combo.");
			throw new CommandException("No user for that username and password combo.");
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new CommandException(ex);
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
