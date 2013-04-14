package dom.model.user;

import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import dom.model.group.IGroup;
import dom.model.invite.Invite;
import dom.model.user.mappers.UserMapper;


public class UserProxy extends DomainObjectProxy<Long, User> implements IUser{

	private int id;
	private User user;
	
	public UserProxy(Long id){
		super(id);
	}
	
	public User getUser(){
		if(user == null){
			user = UserMapper.find(id);
		}
		return user;
	}

	@Override
	protected User getFromMapper(Long id) throws DomainObjectCreationException {
		try{
			return UserMapper.find(id);
		}
		catch(MapperException ex){
			throw new DomainObjectCreationException(ex.getMessage(),ex);
		}
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