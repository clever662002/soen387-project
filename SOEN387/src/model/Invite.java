package model;

/**
 * Domain model representing an Invite
 * @author Alex Perkins
 */
public class Invite implements IInvite{

	private int id;
	private int version;
	private IGroup group;
	public int groupId;
	public IUser user;
	
	public Invite(){
		
	}
	
	public Invite(int id, IGroup group, int version){
		this.id = id;
		this.version = version;
		this.group = group;
	}
	
	public Invite(IGroup group){
		this.group = group;
	}
	
	public int getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public IGroup getGroup() {
		return group;
	}
	
	public void setGroup(IGroup group) {
		this.group = group;
	}
	
	public IGroup getGroupId() {
		return group;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public IUser getUser(){
		return user;
	}
	
	public void setUser(IUser user){
		this.user = user;
	}
	
}