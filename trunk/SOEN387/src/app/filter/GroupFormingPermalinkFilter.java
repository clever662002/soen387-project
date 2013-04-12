package app.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

/**
 * Servlet Filter implementation class BuddyAgePermalinkFilter
 */
public class GroupFormingPermalinkFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GroupFormingPermalinkFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String command = getCommandFromPathInfoAndMaybeSetSomeAttributes((HttpServletRequest)request);
		request.setAttribute("command", command);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	private String getCommandFromPathInfoAndMaybeSetSomeAttributes(
			HttpServletRequest request) {
		System.out.println("\nstart");
		String path = request.getPathInfo();		
	    System.out.println("path = " + path);
		if (path == null)
			return null;
		
		request.setAttribute("prev_path", path);
		
		String command = null;
		if ("/browse_group".equals(path)) {
			command = "BrowseGroupDispatcher";
		}		
		else 
		{
			Pattern pattern = Pattern.compile("^/(view_group|edit_group)/(\\d+)$");
			Matcher matcher = pattern.matcher(path);
			if (matcher.find()) 
			{
				String id = null;
				String strCommand = matcher.group(1);
				if (strCommand.equals("view_group"))
				{	
					//command = "view".equals(matcher.group(1)) ? "ViewPerson" : "IncreaseAge";
					command = "ViewGroupDispatcher";
					
					//set group_id
					id = matcher.group(2);					
					request.setAttribute("group_id", id);					
				}
				else if(strCommand.equals("edit_group"))
				{
					command = "EditGroupDispatcher";
					
					id = matcher.group(2);
					request.setAttribute("group_id", id);
				}
				
				System.out.println("group_id=[" + id + "]");
			}
		}
		
		System.out.println("command = " + command);
		return command;
	}

}
