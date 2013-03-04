package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import data.GroupTDG;
import data.UserTDG;
import model.Group;

public class GroupMapper {
	
	private static final String    ID      = "group_id";
	private static final String    NAME    = "name";
	private static final String    DESC    = "description";
	private static final String    VERSION = "version";
	
	public static List<Group> findAll()throws SQLException{
	
		List<Group> group = new Vector<Group>();
		ResultSet rs = GroupTDG.findAll();
		while(rs.next()) {
			group.add(getGroup(rs));
		}
		return group;

	}
	public static Group find(int id)
	{
		Group result = null;
		try
		{
			
			ResultSet rs = GroupTDG.find(id);
			if(rs.next())
			{
				result = new Group(rs.getInt(ID),
								   rs.getString(NAME),
								   rs.getString(DESC),
								   rs.getInt(VERSION));
			}
			
		}
		catch(SQLException ex)
		{
			System.err.print("SQLException : " + ex.getMessage());
		}
		
		return result;
	}
	
	public static Group find(String group_name)
	{
		Group result = null;
		try
		{
			ResultSet rs = GroupTDG.find(group_name);
			if(rs.next())
			{
				result = new Group(rs.getInt(ID),
								   rs.getString(NAME),
								   rs.getString(DESC),
								   rs.getInt(VERSION));
			}
		}
		catch(SQLException ex)
		{
			System.err.print("SQLException : " + ex.getMessage());
		}
		return result;
	}
	

	private static Group getGroup(ResultSet rs) throws SQLException {
		Group result = new Group(rs.getInt("g.group_id"), rs.getString("g.name"),rs.getString("g.description"),rs.getInt("g.version"));
		return result;
	}
	
	public static Group insert(String name, String description) throws SQLException
	{
		GroupTDG.insert(name, description);
		return GroupMapper.find(name);		
	}
	
	public static Group update(Group g) throws SQLException
	{
		int count = GroupTDG.update(g.getId(), g.getName(), g.getDescription(), g.getVersion());
		if(count == 0)
		{
			throw new SQLException("Fail to update group id=" + g.getId());
		}
		g.setVersion(g.getVersion()+1);
		return GroupMapper.find(g.getId());
	}
	
	public static void delete(Group g) throws SQLException
	{
		int count = GroupTDG.delete(g.getId(), g.getName(), g.getDescription(), g.getVersion());
		if(count == 0)
		{
			throw new SQLException("Fail to delete group id=" + g.getId());
		}
	}
}
