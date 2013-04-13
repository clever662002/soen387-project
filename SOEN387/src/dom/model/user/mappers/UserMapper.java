package dom.model.user.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.IOutputMapper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.mapper.RoleInputMapper;

import dom.model.group.Group;
import dom.model.group.GroupProxy;
import dom.model.invite.Invite;
import dom.model.user.User;
import dom.model.user.UserFactory;
import dom.model.user.UserIdentityMap;
import dom.model.user.tdg.UserTDG;


/**
 * User Mapper Class
 * @author Alex Perkins
 */
public class UserMapper implements IOutputMapper<Long, DomainObject<Long>>{

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
	
	public static User makeUser(ResultSet rs) throws SQLException{
		User user = null;
		try{
			user = UserFactory.createClean(rs.getLong(USER_ID), rs.getString(USERNAME), rs.getString(FIRST_NAME), 
										rs.getString(LAST_NAME), rs.getString(PASSWORD), null, rs.getInt(VERSION));
			
			// Get the users roles
			List<IRole> roles = RoleInputMapper.find(user);
			user.setRoles(roles);
			int groupID = rs.getInt(GROUP_ID);
			GroupProxy gp = null;
			if(groupID > 0){
				gp = new GroupProxy(rs.getInt(GROUP_ID));
			}
			user.setGroup(gp);
		}
		catch(Exception ex){ ex.printStackTrace(); }
		return user;
	}
	
	public static User find(long id) throws MapperException {
		User user = null;
		try{
			if(UserIdentityMap.isKnown(id)){
				return UserIdentityMap.get(id);
			}
			ResultSet rs = UserTDG.find(id);
			if(!rs.next()) throw new MapperException("User with that id doesn't exist!");
			user = makeUser(rs);
			//Put the user in the identity map
			UserIdentityMap.put(user.getId(), user);
		}
		catch(SQLException ex){
			throw new MapperException("Could not find user with id " + id + ".");
		}
		return user;
	}
	
	public static User find(String username) throws MapperException {
		User user = null;
		try{
			ResultSet rs = UserTDG.find(username);
			if(!rs.next()) throw new MapperException("User with that username doesn't exist!");
			user = makeUser(rs);
		}
		catch(SQLException ex){
			throw new MapperException("No user with username " + username);
		}
		return user;
	}
	
	public static User find(String username, String password) throws Exception, SQLException, MapperException{
		User user = null;
		ResultSet rs = UserTDG.find(username,password);
		if(!rs.next()) throw new MapperException("User with that Username/Password doesn't exist!");
		user = makeUser(rs);
		return user;
	}
	
	/**
	 * Find all Users
	 * @return list of users
	 */
	public static List<User> findAll() throws Exception{
		List<User> result = new Vector<User>();
		try{
			ResultSet rs = UserTDG.findAll();
			while(rs.next()) {
				result.add(makeUser(rs));
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
				result = makeUser(rs);

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
	 * Inserts a user into the db
	 * @param user the user to insert
	 */
	public static void insert(User user) throws SQLException, MapperException{
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(USERNAME,user.getUsername());
		params.put(FIRST_NAME,user.getFirstName());
		params.put(LAST_NAME,user.getLastName());
		params.put(PASSWORD,user.getPwd());
		params.put(VERSION,user.getVersion()+"");
		UserTDG.insert(params);
		insertRoles(user);
	}
	
	/**
	 * Updates a user
	 * @param user
	 */
	public static void update(User user) throws MapperException{
		try{
			HashMap<String,String> params = new HashMap<String,String>();
			params.put(USERNAME,user.getUsername());
			params.put(FIRST_NAME,user.getFirstName());
			params.put(LAST_NAME,user.getLastName());
			params.put(PASSWORD,user.getPassword());
			UserTDG.update(params);
			user.getRoles().size(); // We do this to make sure the proxies are loaded.
			deleteRoles(user);
			insertRoles(user);
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
	
	public static boolean isAdmin(long userId){
		boolean isAdmin = false;
		try{
			ResultSet rs = UserTDG.isAdmin(userId);
			if(rs.next()){
				isAdmin = true;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return isAdmin;
	}
	
	public static void deleteRoles(User d) throws SQLException {
		UserTDG.deleteUserRole(d.getId());
	}

	public static void insertRoles(User d) throws SQLException, MapperException {
		for(IRole r: d.getRoles()) {
			int result = UserTDG.insertUserRole(d.getId(), r.getId());
			if(result == 0) throw new MapperException("Unable to insert User Role: " + d.getUsername() + 
					"("+d.getId()+") " + r.getName() + "(" + r.getId() + ")");
		}
	}

	@Override
	public void insert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateInsert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateUpdate(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateDelete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeInsert(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeUpdate(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeDelete(DomainObject<Long> d) throws MapperException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}