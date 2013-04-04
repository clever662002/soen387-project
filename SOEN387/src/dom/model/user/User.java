package dom.model.user;

import java.util.ArrayList;
import java.util.List;


import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.role.IRole;

import dom.model.group.GroupProxy;
import dom.model.group.IGroup;
import dom.model.invite.Invite;

/**
 * Domain model representing a user
 * @author Alex
 */
public class User extends org.dsrg.soenea.domain.user.User implements IUser{

	private String firstName;
	private String lastName;
	private List<Invite> invites;
	private IGroup group;
	private boolean isAdmin;
	
	public User(Long id, String firstName, String lastName, String username,String password, int version, List<IRole> roles){
		super(id,version,username,roles);
		this.firstName = firstName;
		this.lastName = lastName;
		this.invites = new ArrayList<Invite>();
		isAdmin = false;
	}
	
	public String toString(){
		return "User ID: "+super.getId()+ ", username: " + super.getUsername()+ ", first name: " +firstName+ ", last name: " + lastName;
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
