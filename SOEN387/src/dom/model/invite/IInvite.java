package dom.model.invite;

import java.util.List;

import dom.model.group.IGroup;


public interface IInvite {
	public int getId();
	public IGroup getGroup();
	public int getVersion();
}
