package dom.model.invite;

import dom.model.group.IGroup;


public class InviteProxy implements IInvite {

	private int id;
	private Invite invite;
	private IGroup group;
	
	public InviteProxy(int id){
		this.id = id;
	}
	
	public Invite getInvite(){
		if(invite == null){
			//user = InviteMapper.find(id);
		}
		return invite;
	}

	public int getId() {
		return getInvite().getId();
	}

	public void setId(int id) {
		this.id = id;
	}

	public IGroup getGroup(){
		return getInvite().getGroup();
	}
	
	public int getVersion(){
		return getInvite().getVersion();
	}
	
}
