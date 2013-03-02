package model;

/**
 * Domain model representing a user
 * @author Alex
 */
public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password; //Should we keep the password in the object??
	private int version;
	
	public User(long id, String firstName, String lastName, String username, int version){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.username=username;
		this.version=version;
	}
	
	public String toString(){
		return "User ID: "+id+ ", username: " + username+ ", first name: " +firstName+ ", last name: " + lastName;
	}
	
}
