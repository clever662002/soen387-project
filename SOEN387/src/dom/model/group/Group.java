package dom.model.group;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

import dom.model.user.IUser;


/**
 * Domain model representing a Group
 * @author Alex Perkins, Dan Wang
 */
public class Group extends DomainObject<Long> implements IGroup{

	private long id;
	private String name;
	private String description;
	private List<IUser> users;
	private long version;

	
	public Group(int id, String name, String description, long version) {
		super(version);
		this.id = id;
		this.name = name;
		this.description = description;
	//	this.version = version;
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
	
	public Long getId() {
		return id;
	}
	
//	public void setId(int id) {
//		this.id = id;
//	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public void addUser(IUser user){
		users.add(user);
	}
	
	public List<IUser> getUsers(){
		return users;
	}

	public String toString() {
		return "name is : " + this.name + "des is :" + this.description + "id is :" + this.id;
	}

}
