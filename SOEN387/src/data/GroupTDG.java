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
	
	private static String SELECT = "SELECT * FROM " + DB_NAME + " WHERE username=?";
	private static String SELECT_ALL = "SELECT * FROM " + DB_NAME;
	private static String FIND = "SELECT * FROM "+DB_NAME+","+DB_PREFIX+"user_group WHERE groups.group_id=user_group.group_id AND user_group.user_id =?";
	
	public static ResultSet find(String username) throws SQLException {
	/*	
		User result = null;		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_BY_USERNAME);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
		*/
		return null;
	}
	
	public static ResultSet find(int id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
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
	
}
