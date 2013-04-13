package dom.model.user;

import java.util.List;

import dom.model.group.IGroup;
import dom.model.invite.Invite;

public interface IUser {
	public String getFirstName();
	public String getLastName();
	public String getUsername();
	public IGroup getGroup();
	public List<Invite> getInvites();
}
