package dom.model.group.tdg;

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
	private static String INSERT = "INSERT INTO " + TABLE + " (name,description) " + " VALUES(?,?)";
	private static String UPDATE = "UPDATE " + TABLE + " SET group_id = ?, name = ?, description = ?, version = ? WHERE group_id = ?";
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
		//ps.close();
		return rs;
	}
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_ID);
		ps.setString(1, id+"");
		System.out.println(ps.toString());
		ResultSet rs = ps.executeQuery();
		//ps.close();
		return rs;
	}

	
	//public static void insert(int group_id, String name, String description) 
	public static void insert(String name, String description) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);

		ps.setString(1, name);
		ps.setString(2, description);		
//		ps.executeUpdate();
		System.out.print(ps.executeUpdate());
		ps.close();
	}
	
	public static int update(Long long1, String name, String description, long l) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setLong(1, long1);
		ps.setString(2, name);
		ps.setString(3, description);
		ps.setLong(4, l);
		ps.setLong(5, long1);
		
		System.out.println("sql=[" + ps.toString() + "]");
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
	
	public static int delete(Long long1, String name, String description, long l) throws SQLException
	{		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setLong(1, long1);
		//ps.setInt(2, version);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}
}
