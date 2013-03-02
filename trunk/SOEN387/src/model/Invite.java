package model;

/**
 * Domain model representing an Invite
 * @author Alex Perkins
 */
public class Invite {

	private int id;
	private long version;
	private Group group;
	
	public Invite(int id, Group group, long version){
		this.id = id;
		this.version = version;
		this.group = group;
	}
	
	public int getId() {
		return id;
	}
	
	public long getVersion() {
		return version;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
}
