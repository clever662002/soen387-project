package utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for things related to security 
 * @author Alex Perkins
 */
public class SecurityUtil {

	/**
	 *  
	 * @param request
	 * @return whether the user is authenticated
	 */
	public static boolean isAuthenticated(HttpServletRequest request){
		return (request.getSession().getAttribute("user_id") != null);
	}
	
	/**
	 *  
	 * @param request
	 * @return whether the user is authenticated
	 */
	public static boolean isAdmin(HttpServletRequest request){
		return (isAuthenticated(request) && (request.getSession().getAttribute("admin") != null));
	}
	
}
