package dom.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.RoleFactory;

import utils.FileUploadUtil;

import dom.model.user.User;
import dom.model.user.UserFactory;
import dom.model.user.mappers.UserMapper;
import dom.model.user.tdg.UserTDG;

public class AddUsersCommand extends Command{

	private static final List<String> validExtensions = Arrays.asList("csv"); 
	
	private static final String DELIMITER = ",";
	
	public AddUsersCommand(Helper helper){
		super(helper);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void execute() throws CommandException {
		
		try{
		
			//String id = (String) helper.getSessionAttribute("user_id");

			Object obj = helper.getRequestAttribute("fileItems");
			
			if(obj == null){
				throw new CommandException("");
			}
			
			List<FileItem> items = (List<FileItem>)helper.getRequestAttribute("fileItems");
			
			//ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			
			//String[] fileItems = helper.getValues("");
			
			//List fileItemsList = ServletFileUpload.parseRequest(request);
			//Iterator it = fileItemsList.iterator();
		//	FileUploadUtil fuu = new FileUploadUtil(helper.getString("realPath"));
			
		//	String ext = fuu.getFileNameExtension("");
			
			Iterator<FileItem> it = items.iterator();
			while (it.hasNext()){
				FileItem fileItem = (FileItem)it.next();
				//Check if there is file 
				if (!fileItem.isFormField()){
					String filename = fileItem.getName();
					// Get the file extension
					String extension = filename.substring(filename.lastIndexOf(".")+1,filename.length());
					// Make sure the file has the right extension
					if(validExtensions.contains(extension.toLowerCase())) {
						InputStream filecontent = fileItem.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(filecontent));
						String line = br.readLine();
						int added = 0;					
						while(line != null){
							String info[] = line.split(DELIMITER);
							try{
								String rolesArray[] = info[4].split("-");
								List<IRole> roles = new ArrayList<IRole>();
								for(int x=0;x<rolesArray.length;x++){
									roles.add(RoleFactory.create(Long.parseLong(rolesArray[x].trim())));
								}
								UserMapper.insert(UserFactory.createNew(info[0], info[1], info[2], info[3], roles));
								System.out.println("Info : " + info[0] +" , " + info[1] + " , " + info[2] + " , " + info[3] + " , " + info[4]);
							}
							catch(Exception ex){
								
							}
							added++;
							line = br.readLine();
						}
						getNotifications().add(added+" users added.");
				  }
				  else{
					  throw new CommandException("'"+extension+"' is not a valid extension.");
				  }
			  }
			}
		}
		catch(IOException ex){
			getNotifications().add("There was a problem reading the file.");
		}
		catch(Exception ex){
			getNotifications().add("There was a problem parsing the file.");
		}
		finally{
			helper.setRequestAttribute("fileItems",null);
		}
	}

	@Override
	public void setUp() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() throws CommandError {
		// TODO Auto-generated method stub
		
	}
	
}
