package dom.model.group;

import java.util.List;

import dom.model.group.mappers.GroupMapper;
import dom.model.user.IUser;
import dom.model.user.mappers.UserMapper;


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