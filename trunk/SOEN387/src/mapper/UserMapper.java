package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import model.Group;
import model.GroupProxy;
import model.IGroup;
import model.Invite;
import model.User;
import data.GroupTDG;
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
	//GROUP
	private static String DESCRIPTION = "description";
	private static String NAME = "name";
	
	public static User find(String username) {
		User result = null;
		try{
			ResultSet rs = UserTDG.find(username);
			if(rs.next()) {
				//TODO fix the fact that the version is the same name
				//Group group = new Group(rs.getInt(GROUP_ID),rs.getString(NAME),rs.getString(DESCRIPTION),rs.getInt(VERSION));
				result = new User(rs.getInt(USER_ID), 
						rs.getString(FIRST_NAME),
						rs.getString(LAST_NAME), 
						rs.getString(USERNAME),
						rs.getInt(VERSION));
				
				//Group group = GroupTDG.find(1);
				GroupProxy gp = null;
				int groupID = rs.getInt(GROUP_ID);
				if(groupID > 0){
					gp = new GroupProxy(rs.getInt(GROUP_ID));
				}
				result.setGroup(gp);
			
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
	
	/**
	 * Udates a user
	 * @param user
	 */
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
	
	/**
	 * Find all invites associated to a users
	 * @param id the id of the user
	 * @return list of invites associated to the user id
	 */
	public static List<Invite> findInvites(int userId){
		List<Invite> invites = new ArrayList<Invite>();
		try{
			ResultSet rs = UserTDG.findInvitesByUserId(userId);
			while(rs.next()) {
				//TODO fix version
				Group group = new Group(rs.getInt(GROUP_ID),rs.getString(NAME),rs.getString(DESCRIPTION),rs.getInt(VERSION));
				invites.add(new Invite(rs.getInt(USER_ID),group,rs.getInt(VERSION)));
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return invites;
	}
	
	public static void deleteInvite(int id){
		//TODO implement delete invite
	}
	
	public static void deleteInvites(int userId){
		try{
			int affected = UserTDG.deleteInvites(userId);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
}