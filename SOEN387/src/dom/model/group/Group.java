package dom.model.group;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

import dom.model.user.IUser;


/**
 * Domain model representing a Group
 * @author Alex Perkins, Dan Wang
 */
public class Group extends DomainObject<Long> implements IGroup{

	private String name;
	private String description;
	private List<IUser> users;

	public Group(long id,String name, String description, long version) {
		super(id,version);
		this.name = name;
		this.description = description;
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

	public void addUser(IUser user){
		users.add(user);
	}
	
	public List<IUser> getUsers(){
		return users;
	}

	public String toString() {
		return "name is : " + this.name + "des is :" + this.description + "id is :" + super.getId();
	}

}