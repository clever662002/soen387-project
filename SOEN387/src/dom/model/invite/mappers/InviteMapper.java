package dom.model.invite.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;

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
	
	public static void insert(int userId, int groupId) throws MapperException{
		try{
			//InviteTDG.insert(invite.getUser().getId(),invite.getGroup().getId());
			InviteTDG.insert(userId,groupId);
		}
		catch(SQLException ex){
			//ex.printStackTrace();
			throw new MapperException("That user is already invited to that group.");
		}
	}
	
}
