package dom.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;

public class AddUsersCommand extends Command{

	public AddUsersCommand(Helper helper){
		super(helper);
	}
	
	@Override
	public void execute() throws CommandException {
/*			
		try{
		
			String id = (String) helper.getSessionAttribute("user_id");
			
			//if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				List fileItemsList = servletFileUpload.parseRequest(request);
				Iterator it = fileItemsList.iterator();
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
								UserMapper.insert(new User(info[0],info[1],info[2],info[3]));
								added++;
								line = br.readLine();
							}
							request.setAttribute("info", added+" users added.");
							request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					  }
					  else{
						  request.setAttribute("error", "'"+extension+"' is not a valid extension.");
						  request.getRequestDispatcher(VIEW_NAME).forward(request, response);
					  }
				  }
				}
			//}
			//else{
			///	request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			//}	
		}
		catch(FileUploadException ex){
			request.setAttribute("errors", "There was a problem uploading your file.");
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(IOException ex){
			request.setAttribute("errors", "There was a problem reading the file.");
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
		catch(Exception ex){
			request.setAttribute("errors", "There was a problem parsing the file.");
			request.getRequestDispatcher(VIEW_NAME).forward(request, response);
		}
*/	
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
