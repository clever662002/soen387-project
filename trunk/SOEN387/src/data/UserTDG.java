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
	private static final String DB_NAME_USER_GROUP = DB_PREFIX+"user_group";
	private static final String DB_NAME_ADMIN = DB_PREFIX+"admin";
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
	//// SQL STATEMENTS
	//USER
//	private static String SELECT = "SELECT * FROM " + DB_NAME_USER + " LEFT JOIN " +DB_NAME_USER_GROUP+" ON("+DB_NAME_USER+"."+USER_ID+") WHERE "+ DB_NAME_USER+"."+USER_ID+"=?";
	private static String SELECT = "SELECT * FROM " + DB_NAME_USER + " LEFT JOIN " +DB_NAME_USER_GROUP+" ON("+DB_NAME_USER+"."+USER_ID+"="+ DB_NAME_USER_GROUP + "."+ USER_ID+") WHERE "+ DB_NAME_USER+"."+USER_ID+"=?";
	private static String SELECT_ALL = "SELECT * FROM " + DB_NAME_USER;
	private static String FIND_BY_USERNAME = "SELECT * FROM " + DB_NAME_USER + " LEFT JOIN " +DB_NAME_USER_GROUP+" ON("+DB_NAME_USER+"."+USER_ID+") WHERE "+ USERNAME+"=?";
	//private static String FIND_BY_USERNAME = "SELECT * FROM " + DB_NAME_USER + ","+DB_NAME_GROUP+ " WHERE "+DB_NAME_USER+"."+USER_ID+"="+DB_NAME_GROUP+"."+USER_ID+" AND "+USERNAME+"=?";
	private static String INSERT = "INSERT INTO " +DB_NAME_USER+ " ("+USERNAME+","+FIRST_NAME+","+LAST_NAME+","+PASSWORD+") VALUES (?,?,?,?)";
	//TODO finish update query
	private static String UPDATE = "UPDATE " +DB_NAME_USER+ "SET ";
	private static String DELETE_INVITES = "DELETE FROM  " +DB_NAME_INVITE+ " WHERE "+USER_ID+ "=?";
	//INVITE
	private static String FIND_INVITES = "SELECT * FROM "+DB_NAME_INVITE+", "+
		DB_NAME_GROUP+" WHERE "+
		DB_NAME_GROUP+"."+
		GROUP_ID+"="+
		DB_NAME_INVITE+"."+GROUP_ID+" AND "+DB_NAME_INVITE+"."+USER_ID+"=?";
	private static String IS_ADMIN = "SELECT * FROM "+DB_NAME_ADMIN+" WHERE "+USER_ID+ "=?";
	
	
	public static ResultSet find(String username) throws SQLException {
		User result = null;	
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_BY_USERNAME);
		ps.setString(1, username);
		System.out.println(FIND_BY_USERNAME);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet find(int id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		System.out.println(SELECT);
		ps.setString(1, id+"");
		ResultSet rs = ps.executeQuery();
		//ps.close();
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
	
	public static int update(HashMap<String,String> params) throws SQLException {
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
		return rs;
	}
	
	public static ResultSet findInvitesByUserId(int id) throws SQLException {
		System.out.println(FIND_INVITES);
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_INVITES);
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();
		//ps.close();
		return result;
	}
	
	public static int deleteInvites(int id) throws SQLException {
		System.out.println(DELETE_INVITES);
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE_INVITES);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	
	public static ResultSet isAdmin(int userId) throws SQLException {
		System.out.println(IS_ADMIN);
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(IS_ADMIN);
		ps.setInt(1, userId);
		return ps.executeQuery();
	}
	
}
