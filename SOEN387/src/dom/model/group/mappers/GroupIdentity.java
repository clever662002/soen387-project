package dom.model.group.mappers;

import java.util.*;
import dom.model.group.Group;

public class GroupIdentity {
	private Map<Long, Group> map;
	
	public GroupIdentity() 
	{
		map = new HashMap<Long,Group> ();
	}
	
	public void put(long id, Group p)
	{
		map.put(id, p);
	}
	
	public Group get(long id)
	{
		return map.get(id);
	}
	
	public void remove(long id)
	{
		map.remove(id);
	}

}
