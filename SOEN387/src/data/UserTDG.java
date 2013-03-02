package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import model.User;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	private static final String DB_PREFIX = "groupformation_";
	////DATABASE NAME
	private static final String DB_NAME_USER = DB_PREFIX+"user";
	private static final String DB_NAME_INVITE = DB_PREFIX+"invite";
	private static final String DB_NAME_GROUP = DB_PREFIX+"group";
	////COLUMN NAMES
	//USER
	private static String USER_ID = "user_id";
	private static String FIRST_NAME = "first_name";
	private static String LAST_NAME = "last_name";
	private static String USERNAME = "username";
	private static String PASSWORD = "password";
	private static String VERSION = "version";
	//INVITE
	private static String GROUP_ID = "group_id";
	private static String INVITE_ID = "invite_id";
	//// SQL STATEMENTS
	//USER
	private static String SELECT = "SELECT * FROM " + DB_NAME_USER + " WHERE "+USERNAME+"=?";
	private static String SELECT_ALL = "SELECT * FROM " + DB_NAME_USER;
	private static String FIND_BY_USERNAME = "SELECT * FROM " + DB_NAME_USER + " WHERE "+USERNAME+"=?";
	private static String INSERT = "INSERT INTO " +DB_NAME_USER+ " ("+USERNAME+","+FIRST_NAME+","+LAST_NAME+","+PASSWORD+") VALUES (?,?,?,?)";
	//TODO finish update query
	private static String UPDATE = "UPDATE " +DB_NAME_USER+ "SET ";
	//INVITE
	private static String FIND_INVITES = "SELECT * FROM "+DB_NAME_INVITE+", "+
		DB_NAME_GROUP+" WHERE "+
		DB_NAME_GROUP+"."+
		GROUP_ID+"="+
		DB_NAME_INVITE+"."+GROUP_ID+" AND "+DB_NAME_INVITE+"."+USER_ID+"=?";
	
	
	
	public static ResultSet find(String username) throws SQLException {
		User result = null;		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_BY_USERNAME);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet find(int id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setString(1, id+"");
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
		ps.close();
		return rs;
	}
	
	public static void insert(HashMap<String,String> params) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, params.get(USERNAME));
		ps.setString(2, params.get(FIRST_NAME));
		ps.setString(3, params.get(LAST_NAME));
		
		//TODO hash the password
		String password = params.get(PASSWORD);
		String encryptedPassword = password;
		
		ps.setString(4, encryptedPassword);
		int rs = ps.executeUpdate();
		ps.close();
	}
	
	public static void update(HashMap<String,String> params) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, params.get(USERNAME));
		ps.setString(2, params.get(FIRST_NAME));
		ps.setString(3, params.get(LAST_NAME));
		//TODO hash the password
		String password = params.get(PASSWORD);
		String encryptedPassword = password;
		ps.setString(4, encryptedPassword);
		int rs = ps.executeUpdate();
		ps.close();
	}
	
	public static ResultSet findInvitesByUserId(int id) throws SQLException {
		System.out.println(FIND_INVITES);
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_INVITES);
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();
		ps.close();
		return result;
	}
	
}
