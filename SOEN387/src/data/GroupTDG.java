package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupTDG {

//	private static final String DB_PREFIX = "groupformation_";
//	private static final String DB_NAME = DB_PREFIX+"group";
	
	public static final String BASE_NAME = "group";
	public static final String TABLE = DbRegistry.getTablePrefix()+BASE_NAME;
	
	private static String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE group_id = ?";
	private static String SELECT_BY_NAME = "SELECT * FROM " + TABLE + " WHERE name = ?";
	private static String SELECT_ALL = "SELECT g.group_id, g.name, g.description, g.version FROM " + TABLE + " AS g;";
	//private static String INSERT = "INSERT INTO " + TABLE + " (name,description,version) " + " VALUES((select max(group_id)+1 FROM " + TABLE + ")," +
	//		                           		    " name,description,1)";
	private static String INSERT = "INSERT INTO " + TABLE + " (name,description) " + " VALUES(?,?)";
	private static String UPDATE = "UPDATE " + TABLE + " SET group_id = ?, name = ?, description = ?, version = ? WHERE group_id = ?";
	//private static String UPDATE = "UPDATE " + TABLE + " SET name = ?, description = ?  WHERE group_id = ?";
	private static String DELETE = "DELETE FROM " + TABLE + " WHERE group_id = ?";
	
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
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
		System.out.println(ps.toString());
		ResultSet rs = ps.executeQuery();
		//ps.close();
		return rs;
	}

	
	//public static void insert(int group_id, String name, String description) 
	public static void insert(String name, String description)
	throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		//ps.setInt(1, group_id);
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
		ps.setInt(5, group_id);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
	
	public static int delete(int group_id, String name, String description, int version) throws SQLException
	{
		System.out.println("Group id" + group_id);
		System.out.println(DbRegistry.getDbConnection().prepareStatement(DELETE));
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setInt(1, group_id);
		//ps.setInt(2, version);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
}
