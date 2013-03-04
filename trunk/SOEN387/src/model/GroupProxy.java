package model;

import java.util.List;

import mapper.GroupMapper;
import mapper.UserMapper;

public class GroupProxy implements IGroup {

	private int id;
	private Group group;
	
	public GroupProxy(int id){
		this.id = id;
	}
	
	public Group getGroup(){
		if(group == null){
			group = GroupMapper.find(id);
		}
		return group;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return getGroup().getName();
	}

	@Override
	public String getDescription() {
		return getGroup().getDescription();
	}

	@Override
	public List<IUser> getUsers() {
		return getGroup().getUsers();
	}

	@Override
	public int getVersion() {
		return getGroup().getVersion();
	}
	
}