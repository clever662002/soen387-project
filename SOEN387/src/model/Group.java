package model;

import java.util.List;

/**
 * Domain model representing a Group
 * @author Alex Perkins
 */
public class Group {

	private int id;
	private String name;
	private String description;
	private List<User> users;
	private long version;
	
}
