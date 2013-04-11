package dom.model.user.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

import dom.model.user.User;

public class UserTDG {
	
	private static final String DB_PREFIX = "groupformation_";
	////DATABASE NAME
	private static final String DB_NAME_USER = DB_PREFIX+"user";
	private static final String DB_NAME_INVITE = DB_PREFIX+"invite";
	private static final String DB_NAME_GROUP = DB_PREFIX+"group";
	private static final String DB_NAME_USER_GROUP = DB_PREFIX+"user_group";
	private static final String DB_NAME_ADMIN = DB_PREFIX+"admin";
	
	private static final String DB_USER_ROLE_TABLE = DB_PREFIX+"UserRole";
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
	private static String LOGIN = "SELECT * FROM " + DB_NAME_USER + " LEFT JOIN " +DB_NAME_USER_GROUP+" ON("+DB_NAME_USER+"."+USER_ID+"="+ DB_NAME_USER_GROUP + "."+ USER_ID+") WHERE "+ USERNAME+"=? AND " + PASSWORD + "=?";
	private static String FIND_BY_USERNAME = "SELECT * FROM " + DB_NAME_USER + " LEFT JOIN " +DB_NAME_USER_GROUP+" ON("+DB_NAME_USER+"."+USER_ID+") WHERE "+ USERNAME+"=?";
	//private static String FIND_BY_USERNAME = "SELECT * FROM " + DB_NAME_USER + ","+DB_NAME_GROUP+ " WHERE "+DB_NAME_USER+"."+USER_ID+"="+DB_NAME_GROUP+"."+USER_ID+" AND "+USERNAME+"=?";
	private static String INSERT = "INSERT INTO " +DB_NAME_USER+ " ("+USERNAME+","+FIRST_NAME+","+LAST_NAME+","+PASSWORD+","+VERSION +") VALUES (?,?,?,?,?)";
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
	
	public static String DELETE_USER_ROLE_SQL = 
			"DELETE FROM " + DB_USER_ROLE_TABLE + " " +
			"WHERE user_id=?;";
	
	public static String INSERT_USER_ROLE_SQL = 
			"INSERT INTO " + DB_USER_ROLE_TABLE + " " +
			"(user_id, role_id) VALUES(?, ?);";
	
	public final static String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS " + DB_NAME_USER + " (" +
		"user_id int NOT NULL,"+ //user_id int NOT NULL AUTO_INCREMENT,
		"PRIMARY KEY(user_id),"+
		"username varchar(25) NOT NULL UNIQUE,"+
		"password varchar(25),"+
		"first_name varchar(25)," +
		"last_name varchar(25),"+
		"version int NOT NULL DEFAULT 1"+
		") ENGINE=InnoDB;";

	public final static String DROP_TABLE =
		"DROP TABLE IF EXISTS " + DB_NAME_USER + ";";

	
	public static long getMaxID() throws SQLException {
		return UniqueIdFactory.getMaxId( DB_NAME_USER, "id" );
	}
	
	public static ResultSet find(String username,String password) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(LOGIN);
		ps.setString(1, username);
		ps.setString(2, password);
		System.out.println(LOGIN);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet find(String username) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(FIND_BY_USERNAME);
		ps.setString(1, username);
		//System.out.println(FIND_BY_USERNAME);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		//System.out.println(SELECT);
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
		ps.setInt(5, Integer.parseInt(params.get(VERSION)));		
		int rs = ps.executeUpdate();
		ps.close();
	}
	
	public static int update(HashMap<String,String> params) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
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
	
	public static ResultSet isAdmin(long userId) throws SQLException {
		System.out.println(IS_ADMIN);
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(IS_ADMIN);
		ps.setLong(1, userId);
		return ps.executeQuery();
	}
	
	public static int deleteUserRole(long user_id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_USER_ROLE_SQL);
		ps.setLong(1, user_id);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int insertUserRole(long user_id, long role_id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_USER_ROLE_SQL);
		ps.setLong(1, user_id);
		ps.setLong(2, role_id);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
}
