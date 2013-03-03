package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.User;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupTDG {

	private static final String DB_PREFIX = "groupformation_";
	private static final String DB_NAME = DB_PREFIX+"group";
	
	private static String SELECT_BY_ID = "SELECT * FROM " + DB_NAME + " WHERE group_id = ?";
	private static String SELECT_BY_NAME = "SELECT * FROM " + DB_NAME + " WHERE name = ?";
	private static String SELECT_ALL = "SELECT * FROM " + DB_NAME;
	private static String INSERT = "INSERT INTO " + DB_NAME + " (name,description,version) " +
			                           " VALUES((select max(group_id)+1 FROM " + DB_NAME + ")," +
			                           		    " name,description,1)";
	private static String UPDATE = "UPDATE " + DB_NAME + " SET group_id = ?, name = ?, description = ?, version = ? WHERE group_id = ?";
	private static String DELETE = "DELETE FROM " + DB_NAME + "WHERE group_id = ?";
	
	public static ResultSet find(String group_name) throws SQLException 
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_NAME);
		ps.setString(1, group_name);
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	public static ResultSet find(int id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_ID);
		ps.setString(1, id+"");
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	public static ResultSet findAll() throws SQLException {
		List<User> result = new Vector<User>();
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	public static void insert(String name, String description) 
	throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, name);
		ps.setString(2, description);		
		ps.executeUpdate();
		ps.close();
	}
	
	public static int update(int group_id, String name, String description, int version) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setInt(1, group_id);
		ps.setString(2, name);
		ps.setString(3, description);
		ps.setInt(4, version);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
	
	public static int delete(int group_id, String name, String description, int version) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setInt(1, group_id);
		ps.setInt(2, version);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
}
