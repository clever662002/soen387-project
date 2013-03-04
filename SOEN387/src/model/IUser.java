package model;

import java.util.List;

public interface IUser {
	public int getId();
	public int getVersion();
	public String getFirstName();
	public String getLastName();
	public String getUsername();
	public IGroup getGroup();
	public List<Invite> getInvites();
}
