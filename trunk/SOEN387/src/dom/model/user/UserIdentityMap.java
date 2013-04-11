package dom.model.user;

import java.util.HashMap;
import java.util.Map;

public class UserIdentityMap {
	
	private static Map<Long,User> knownUsers = new HashMap<Long,User>();

	public static void put(long id, User user){
		knownUsers.put(id, user);
	}
	
	public static User get(long id){
		return knownUsers.get(id);
	}
	
	public static boolean isKnown(long id){
		return (get(id) == null);
	}
	
}