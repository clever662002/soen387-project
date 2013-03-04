package model;

import java.util.List;

import mapper.UserMapper;

public class UserProxy implements IUser{

	private int id;
	private User user;
	
	public UserProxy(int id){
		this.id = id;
	}
	
	public User getUser(){
		if(user == null){
			user = UserMapper.find(id);
		}
		return user;
	}

	@Override
	public int getId() {
		return getUser().getId();
	}


	@Override
	public int getVersion() {
		return getUser().getVersion();
	}


	@Override
	public String getFirstName() {
		return getUser().getFirstName();
	}


	@Override
	public String getLastName() {
		return getUser().getLastName();
	}


	@Override
	public String getUsername() {
		return getUser().getUsername();
	}


	@Override
	public IGroup getGroup() {
		return getUser().getGroup();
	}


	@Override
	public List<Invite> getInvites() {
		return getUser().getInvites();
	}


}
