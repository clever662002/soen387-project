package model;

import java.util.List;

public interface IGroup {
	public int getId();
	public String getName();
	public String getDescription();
	public List<IUser> getUsers();
	public int getVersion();
}