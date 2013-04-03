package dom.model.invite.mappers;

import java.sql.SQLException;

import dom.model.invite.Invite;
import dom.model.invite.tdg.InviteTDG;



public class InviteMapper {

	public static void delete(int userId, int groupId) {
		try{
			//InviteTDG.delete(invite.getUser().getId(),invite.getGroup().getId());
			InviteTDG.delete(userId,groupId);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public static void insert(int userId, int groupId) {
		try{
			//InviteTDG.insert(invite.getUser().getId(),invite.getGroup().getId());
			InviteTDG.insert(userId,groupId);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
}
