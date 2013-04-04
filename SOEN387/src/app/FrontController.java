package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.DispatcherServlet;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.dispatcher.HttpServletHelper;
import org.dsrg.soenea.application.servlet.service.DispatcherFactory;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.impl.GuestRole;
import org.dsrg.soenea.domain.role.mapper.RoleOutputMapper;
import org.dsrg.soenea.domain.user.mapper.UserOutputMapper;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.authorization.ApplicationAuthorizaton;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;

import dom.model.role.AdminRole;
import dom.model.role.RegisteredRole;
import dom.model.user.GuestUser;
import dom.model.user.User;
import dom.model.user.mappers.UserMapper;

public class FrontController extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	private static String defaultDispatcher = "";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			defaultDispatcher = Registry.getProperty("defaultDispatcher");
		} catch (Exception e) {}

		ApplicationAuthorizaton.setBasePath(getServletContext().getRealPath("."));
		prepareDbRegistry();
		setupUoW();
	}

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dispatcher command = null;
		Helper myHelper = null;
		String commandName = null;
		User user = null;
		
		try{
			
			myHelper = new HttpServletHelper(request);
			
			for(Object key : request.getParameterMap().keySet()){
				System.out.println("Key " + key + " value: " + request.getParameterValues(key.toString()));
				 
			}
			
			commandName = getCommandName(request);
			
			if(commandName == null){
				commandName = "";
			}
			
			user = (User) myHelper.getSessionAttribute("currentUser");
			
			if(user == null){
				user = new GuestUser();
				request.getSession(true).setAttribute("currentUser", user);
			}
			
			ApplicationAuthorizaton.hasAuthority(commandName,user.getRoles());
			command = DispatcherFactory.getInstance(commandName);
			command.init(request, response);
			long time = System.currentTimeMillis();
			command.execute();
			System.out.println("Total Request time: " + (System.currentTimeMillis() - time) + " ms.");
		}
		catch(Exception ex){
			request.setAttribute("errorMessage", ex.getMessage());
			request.setAttribute("exception", ex);
			request.getRequestDispatcher("/WEB-INF/jsp/html/Error.jsp");
		}
	}
	
	private String getCommandName(HttpServletRequest request) throws Exception{
		
		String commandName = request.getParameter("command");
		
		if(commandName == null || commandName.equals("")){
			if(defaultDispatcher == null){
				throw new Exception("HTTP attribute 'command' is missing.");
			}
			else{
				commandName = defaultDispatcher;
			}
		}
		
		return commandName;
	}

	@Override
	protected void preProcessRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.preProcessRequest(request, response);
		UoW.newCurrent();
		try{
			DbRegistry.getDbConnection().setAutoCommit(false);
			DbRegistry.getDbConnection().createStatement().execute("START TRANSACTION;");
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	

	@Override
	protected void postProcessRequest(HttpServletRequest request, HttpServletResponse response) {
		super.postProcessRequest(request, response);
		try{
			DbRegistry.getDbConnection().createStatement().execute("ROLLBACK;");
			DbRegistry.getDbConnection().close();
			DbRegistry.closeDbConnectionIfNeeded();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		ThreadLocalTracker.purgeThreadLocal();
	}

	public static void setupUoW() {
		MapperFactory myDomain2MapperMapper = new MapperFactory();
		myDomain2MapperMapper.addMapping(User.class, UserMapper.class);
		myDomain2MapperMapper.addMapping(GuestUser.class,
		UserOutputMapper.class);
		myDomain2MapperMapper.addMapping(GuestRole.class,
		RoleOutputMapper.class);
		myDomain2MapperMapper.addMapping(AdminRole.class,
		RoleOutputMapper.class);
		myDomain2MapperMapper.addMapping(RegisteredRole.class,
		RoleOutputMapper.class);
		UoW.initMapperFactory(myDomain2MapperMapper);
	}
	
	public static void prepareDbRegistry() {
		prepareDbRegistry("");
	}
	
	public static void prepareDbRegistry(String db_id) {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id+"mySqlTablePrefix");
		} catch (Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}
	
}