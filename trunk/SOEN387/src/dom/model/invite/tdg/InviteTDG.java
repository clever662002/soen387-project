package dom.model.invite.tdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.dsrg.soenea.service.threadLocal.DbRegistry;

import dom.model.user.User;

public class InviteTDG {

	private static final String DB_PREFIX = "groupformation_";
	////DATABASE NAME
	private static final String DB_NAME = DB_PREFIX+"invite";
	////COLUMN NAMES
	private static final String ID = "invite_id";
	private static final String GROUP_ID = "group_id";
	private static final String USER_ID = "user_id";
	////SQL STATEMENTS
	private static final String DELETE = "DELETE FROM "+DB_NAME+" WHERE "+GROUP_ID+"=? AND " + USER_ID + "=?";
	private static final String INSERT = "INSERT INTO "+DB_NAME+" ("+GROUP_ID+","+USER_ID+") VALUES (?,?)";
	
	public static void delete(int userId,int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setInt(1, groupId);
		ps.setInt(2, userId);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void insert(int userId,int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setInt(1, groupId);
		ps.setInt(2, userId);
		ps.executeUpdate();
		ps.close();
	}
	
}
