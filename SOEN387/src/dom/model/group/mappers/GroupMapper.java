package dom.model.group.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;


import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.IOutputMapper;

import dom.model.group.Group;
import dom.model.group.tdg.GroupTDG;
import dom.model.user.tdg.UserTDG;


public class GroupMapper implements IOutputMapper<Long, DomainObject<Long>>{
	
	private static final String    ID      = "group_id";
	private static final String    NAME    = "name";
	private static final String    DESC    = "description";
	private static final String    VERSION = "version";
	private static GroupIdentity   map     = new GroupIdentity();
	
	public static List<Group> findAll() throws SQLException
	{
		List<Group> list_group = new Vector<Group>();
		List<Long> list_id = new Vector<Long>();
		
		ResultSet rs = GroupTDG.findAll();
		while(rs.next())
		{
			Group g = getGroup(rs);
			list_group.add(g);
			
			if(map.get(g.getId()) == null)
			{
				map.put(g.getId(), g);
			}
		}
		
		
		return list_group;
	}
	
	
	public static Group find(long id) {		
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
				
				if(map.get(result.getId())== null)
				{					
					map.put(result.getId(), result);
				}				
			}						
		}
		catch(SQLException ex)		
		{
			System.err.print("SQLException : " + ex.getMessage());
		}
		
		return result;
	}
	
	public static Group find(String group_name) {
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
				if(map.get(result.getId()) == null)
				{
					map.put(result.getId(), result);
				}
			}
		}
		catch(SQLException ex)
		{
			System.err.print("SQLException : " + ex.getMessage());
		}
		return result;
	}
	
	/*
	public static List<Group> findAll() 
	{		
		List<Group> group = new Vector<Group>();
		try
		{				
			ResultSet rs = GroupTDG.findAll();
			while(rs.next()) 
			{
				group.add(getGroup(rs));
			}		
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return group;		
	}
	
	public static Group find(long id)
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
				System.out.print(result.getId());
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
	*/
	private static Group getGroup(ResultSet rs) throws SQLException {		
		Group result = new Group(rs.getInt("g.group_id"), rs.getString("g.name"),rs.getString("g.description"),rs.getInt("g.version"));
		return result;
	}
	
	public static Group insert(String name, String description) throws SQLException
	//public static Group insert(int group_id,String name, String description) throws SQLException
	{	
		GroupTDG.insert(name, description);
		return GroupMapper.find(name);		
	}
	
	/*
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
	*/
	
	public static Group update(Group g) throws SQLException
	{			
		// update db
		int count = GroupTDG.update(g.getId(), g.getName(), g.getDescription(), g.getVersion());
		if(count == 0)
		{			
			throw new SQLException("Fail to update group id=" + g.getId());
		}
		
		g.setVersion(g.getVersion()+1);
		
		// refresh map identiy, if currently in memory
		if(map.get(g.getId())!=null)
		{
			map.get(g.getId()).setName(g.getName());
			map.get(g.getId()).setDescription(g.getName());
			map.get(g.getId()).setVersion(g.getVersion());
		}
		

		return GroupMapper.find(g.getId());
	}
	
	public static void delete(Group g) throws SQLException
	{		
		if(map.get(g.getId())!=null)
		{
			map.remove(g.getId());
		}
		
		int count = GroupTDG.delete(g.getId(), g.getName(), g.getDescription(), g.getVersion());
		if(count == 0)
		{
			throw new SQLException("Fail to delete group id=" + g.getId());
		}				
	}
	
	@Override
	public void insert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeInsert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeUpdate(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeDelete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateInsert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateUpdate(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateDelete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}
}
