package dom.model.user;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.RoleFactory;
import org.dsrg.soenea.service.registry.Registry;

/**
 * A simple POJO for representing Guest Users
 * 
 * @author Stuart Thiel
 *
 */
public class GuestUser extends User {
	
	public static int GUEST_ID = 1;
	/**
	 * It is important to note that the MyResources.Properties file should reflect the fact that ConcreteRole_1 is the guest role.
	 */

	static {
		try {
			GUEST_ID = Registry.getInt("GUEST_USER_ID");
		} catch (Exception e) {}
	}
	
	private static List<IRole> guestRoles = new ArrayList<IRole>();
	static {
		try {
			guestRoles.add(RoleFactory.create(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GuestUser() {
		super(GUEST_ID, 1, "Guest", guestRoles);
	}

}
