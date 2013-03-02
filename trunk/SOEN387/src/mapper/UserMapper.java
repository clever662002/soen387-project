package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.User;
import data.UserTDG;

public class UserMapper {

	private static String ID = "user_id";
	private static String FIRST_NAME = "first_name";
	private static String LAST_NAME = "last_name";
	private static String USERNAME = "username";
	private static String PASSWORD = "password";
	private static String VERSION = "version";
	
	public static User find(String username) {
		User result = null;
		try{
			ResultSet rs = UserTDG.find(username);
			if(rs.next()) {
				result = new User(rs.getInt(ID), 
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
	
	public static List<User> findAll() {
		List<User> result = new Vector<User>();
		try{
			ResultSet rs = UserTDG.findAll();
			while(rs.next()) {
				result.add(new User(rs.getInt(ID), 
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
	
	public static User find(int id) {
		User result = null;
		try{
			ResultSet rs = UserTDG.find(id);
			if(rs.next()) {
				result = new User(rs.getInt(ID), 
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
	
}