package dom.model.user;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.uow.UoW;

import dom.model.user.tdg.UserTDG;

public class UserFactory {

	public static User createNew(String username, String firstName, String lastName, String password,List<IRole> roles) throws SQLException, MapperException {
		User user = new User(UserTDG.getMaxID(),firstName,lastName,username,password,1,roles);
		UoW.getCurrent().registerNew(user);
		return user;
	}
		  
	public static User createClean(long id, String username, String firstName, String lastName, String password,List<IRole> roles, int version) {
		User user = new User(id,firstName,lastName,username,password,1,roles);
		UoW.getCurrent().registerClean(user);
		return user;
	}
}
