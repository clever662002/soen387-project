package dom.model.group;

import java.util.List;

import dom.model.user.IUser;


public interface IGroup {
	public int getId();
	public String getName();
	public String getDescription();
	public List<IUser> getUsers();
	public int getVersion();
}