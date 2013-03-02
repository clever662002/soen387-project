package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import mapper.UserMapper;
import model.User;

public class AddUsersPC extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final List<String> validExtensions = Arrays.asList("csv"); 
	
	private static final String DELIMITER = ",";
	
	private static final String VIEW_NAME = "/jsp/AddUsersTV.jsp";
	
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			if(ServletFileUpload.isMultipartContent(request)){
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
			}
			else{
				request.getRequestDispatcher(VIEW_NAME).forward(request, response);
			}	
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
	}

}