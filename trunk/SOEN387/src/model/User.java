package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain model representing a user
 * @author Alex
 */
public class User implements IUser{

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password; //Should we keep the password in the object??
	private int version;
	private List<Invite> invites;
	private IGroup group;
	private boolean isAdmin;
	
	public User(int id, String firstName, String lastName, String username, int version){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.version = version;
		this.invites = new ArrayList<Invite>();
		isAdmin = false;
	}
	
	public User(String username, String firstName, String lastName, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.invites = new ArrayList<Invite>();
		isAdmin = false;
	}
	
	public String toString(){
		return "User ID: "+id+ ", username: " + username+ ", first name: " +firstName+ ", last name: " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
	
	public void setInvites(List<Invite> invites){
		this.invites = invites;
	}
	
	public List<Invite> getInvites(){
		return invites;
	}
	
	public IGroup getGroup(){
		return group;
	}
	
	public void setGroup(IGroup group){
		this.group = group;
	}
	
	public boolean hasGroup(){
		return (group != null && group.getId() > 0);
	}
	
}
