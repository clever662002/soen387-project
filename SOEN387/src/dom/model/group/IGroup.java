package dom.model.group;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;

import dom.model.user.IUser;


public interface IGroup extends IDomainObject<Long>{
//	public Integer getId();
	public String getName();
	public void setName(String name);
//	public String getDescription();
	public List<IUser> getUsers();
//	public long getVersion();
}