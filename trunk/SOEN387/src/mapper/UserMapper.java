package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import model.Group;
import model.Invite;
import model.User;
import data.UserTDG;

/**
 * User Mapper Class
 * @author Alex Perkins
 */
public class UserMapper {

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
	//GROUP
	private static String DESCRIPTION = "description";
	private static String NAME = "name";
	
	public static User find(String username) {
		User result = null;
		try{
			ResultSet rs = UserTDG.find(username);
			if(rs.next()) {
				result = new User(rs.getLong(USER_ID), 
						rs.getString(FIRST_NAME),
						rs.getString(LAST_NAME), 
						rs.getString(USERNAME),
						rs.getInt(VERSION));
			}
		}
		catch(SQLException ex){
			System.err.print("SQLException : " + ex.getMessage());
		}
		return result;
	}
	
	/**
	 * Find all Users
	 * @return list of users
	 */
	public static List<User> findAll() {
		List<User> result = new Vector<User>();
		try{
			ResultSet rs = UserTDG.findAll();
			while(rs.next()) {
				result.add(new User(rs.getInt(USER_ID), 
						rs.getString(FIRST_NAME),
						rs.getString(LAST_NAME), 
						rs.getString(USERNAME),
						rs.getInt(VERSION)));
			}
		}
		catch(SQLException ex){
			System.err.print("SQLException : " + ex.getMessage());
		}
		return result;
	}
	
	/**
	 * Finds users by id
	 * @param id
	 * @return
	 */
	public static User find(int id) {
		User result = null;
		try{
			ResultSet rs = UserTDG.find(id);
			if(rs.next()){
				result= new User(rs.getInt(USER_ID), 
						rs.getString(FIRST_NAME),
						rs.getString(LAST_NAME), 
						rs.getString(USERNAME),
						rs.getInt(VERSION));
			}
		}
		catch(SQLException ex){
			System.err.print("SQLException : " + ex.getMessage());
		}
		return result;
	}
	
	/**
	 * Inserts a user into the db
	 * @param user the user to insert
	 */
	public static void insert(User user){
		try{
			HashMap<String,String> params = new HashMap<String,String>();
			params.put(USERNAME,user.getUsername());
			params.put(FIRST_NAME,user.getFirstName());
			params.put(LAST_NAME,user.getLastName());
			params.put(PASSWORD,user.getPassword());
			UserTDG.insert(params);
		}
		catch(SQLException ex){
			System.err.print("SQLException : " + ex.getMessage());
		}
	}
	
	public static void update(User user){
		try{
			HashMap<String,String> params = new HashMap<String,String>();
			params.put(USERNAME,user.getUsername());
			params.put(FIRST_NAME,user.getFirstName());
			params.put(LAST_NAME,user.getLastName());
			params.put(PASSWORD,user.getPassword());
			UserTDG.update(params);
		}
		catch(SQLException ex){
			System.err.print("SQLException : " + ex.getMessage());
		}
	}
	
	
	public static List<Invite> findInvites(int id){
		List<Invite> invites = new ArrayList<Invite>();
		try{
			ResultSet rs = UserTDG.findInvitesByUserId(id);
			while(rs.next()) {
			//TODO add group
				/*
				Group group = new Group();
				group.description = rs.getString(DESCRIPTION);
				group.name = rs.getString(NAME);
				*/
				invites.add(new Invite(rs.getInt(INVITE_ID),null,rs.getLong(VERSION)));
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return invites;
	}
	
}