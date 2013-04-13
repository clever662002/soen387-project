package dom.model.group;

import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import dom.model.group.mappers.GroupMapper;
import dom.model.user.IUser;

public class GroupProxy extends DomainObjectProxy<Long,Group>implements IGroup {

	private Group group;
	
	public GroupProxy(long l){
		super(l);
	}
	
	public Group getGroup(){
		if(group == null){
			group = GroupMapper.find(super.getId());
		}
		return group;
	}

	@Override
	public String getDescription() {
		return getGroup().getDescription();
	}

	@Override
	public List<IUser> getMembers() {
		return getGroup().getMembers();
	}

	@Override
	public long getVersion() {
		return getGroup().getVersion();
	}

	@Override
	public void setVersion(long new_version) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return getGroup().getName();
	}

	@Override
	protected Group getFromMapper(Long id) throws MapperException,
			DomainObjectCreationException {
		// TODO Auto-generated method stub
		return null;
	}

}