package model;

import java.util.List;

/**
 * Domain model representing a Group
 * @author Alex Perkins, Dan Wang
 */
public class Group implements IGroup{

	private int id;
	private String name;
	private String description;
	private List<IUser> users;
	private int version;

	
	public Group(int id, String name, String description, int version) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.version = version;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void addUser(IUser user){
		users.add(user);
	}
	
	public List<IUser> getUsers(){
		return users;
	}

}
