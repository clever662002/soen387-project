package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dsrg.soenea.service.PropertyRegistry;
import org.dsrg.soenea.service.PropertyRegistry.PropertyInUseException;
import org.dsrg.soenea.service.registry.Registry;

/**
 * "BORROWED" FROM :
 * http://beanstore.encs.concordia.ca/portal2010/trac/browser/portal/trunk
 * @author Not Alex Perkins
 */
public class FileUploadUtil
{
	private static String propertyFile = "Myresources.properties";
	private static String savedDirPropertyName = "saveUploadDir";
	private static String uploadDirPropertyName = "tempUploadDir";
	
    private static String UPLOAD_DIR = "WEB-INF/uploads/temp";
    private static String SAVED_DIR = "WEB-INF/uploads/saved";
    
	private static final int MAX_FILE_MEMORY_SIZE = 1024*90; // bigger files written to disk
	public static final int MAX_FILE_SIZE = 1024*1024*2000; // bigger files rejected
	private static final int MAX_REQUEST_SIZE = 1024*1024*2000; // overall size of request; bigger is rejected
    
	static {
		try {
			PropertyRegistry.registerProperty(uploadDirPropertyName, new PropertyRegistry.Property(uploadDirPropertyName, 
					"This property should contain the directory name, relative to the realPath, where temp files should get stored.", propertyFile));
			PropertyRegistry.registerProperty(savedDirPropertyName, new PropertyRegistry.Property(savedDirPropertyName, 
					"This property should contain the directory name, relative to the realPath, where files that we want to keep should get stored.", propertyFile));
			UPLOAD_DIR = Registry.getProperty(uploadDirPropertyName);
			SAVED_DIR = Registry.getProperty(savedDirPropertyName);
		} catch (PropertyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//Logging.log(Level.ALL, "Are you sure that " + savedDirPropertyName + " and " + uploadDirPropertyName + " are defined in " + propertyFile + "?");
			e.printStackTrace();
		}
		
	}	    
    //This should match what is in the FilePermissions of the catalina policy. Either way, should be loaded from MyResources.properties.
    private File uploadDir = null;
    private File savedDir = null;
    private final String realPath;

    
    /**
     * @param realPath set in the request by IYPFrontController
     */
    public FileUploadUtil(String realPath)
    {
        if (realPath==null)
            throw new NullPointerException();
        this.realPath = realPath;
    }
    
    public synchronized File getUploadDir() throws IOException
    {
        if (uploadDir != null)
            return uploadDir;
        uploadDir = new File(realPath, UPLOAD_DIR + "/");
        if (!uploadDir.isDirectory() && !uploadDir.mkdirs())
            throw new IOException("could not create new file:" + uploadDir.getCanonicalPath());
        return uploadDir;
    }
    public synchronized File ensureSavedDir() throws IOException
    {
        if (savedDir != null)
            return savedDir;
        savedDir = new File(realPath, SAVED_DIR + "/");      
        if (!savedDir.isDirectory() && !savedDir.mkdirs())
            throw new IOException("could not create new file:" + savedDir.getCanonicalPath());
        return savedDir;
    }
 
    /**
     * Gets a file path relative to realPath.
     * 
     * @throws IOException 
     */
    public String getRelativeFilePath(File f) throws IOException {
    	return f.getCanonicalPath().substring(realPath.length()-1);
    }
    
    public String getAbsoluteFilePath(String relativeFilePath) throws IOException {
    	return realPath+File.separatorChar+relativeFilePath;
    }
    
    public static String getFileNameExtension(String filename)
    {
        int i = filename.lastIndexOf('.');
        filename = filename.substring(i+1);
        return filename;
    }
    
    /**
     * Tries to delete a previously saved file. if an error occurs, it should store the error somewhere
     * that a regularly scheduled job can later try to delete it again.
     * @param name
     */
    public void deleteFile(String relative_path)
    {
        if (relative_path == null || relative_path.length() == 0)
            return;
        try {
        	File file = new File(realPath + "/" + relative_path);
            System.out.println("Deleting: " + file.getCanonicalFile());
            if (file == null)
                return;
            if (!file.delete()) {
            //  this should actually log somewhere specific so it can be regularly parsed
            //    Logging.log(Level.WARNING, "COULD NOT DELETE : " + file.getAbsolutePath());
            }
        } catch (IOException ioe) {
           // Logger.getLogger(PortalFrontController.LOG_STRING).throwing("FileUploadUtil", "deleteSavedFile", ioe);
        }
    }
    
    public File getTempFile(String extension) throws IOException {
    	return File.createTempFile("upload", "." + extension, getUploadDir());
    }
    
    /**
     * Copies a file from one location to another.
     * 
     * @param relative_path
     * @return The new relative path of the permanent file.
     * @throws IOException
     */
    public String copyTempFileToPermanent(String relative_path) throws IOException {
    	ensureSavedDir();
    	File temp_file = new File(realPath + "/" + relative_path);
    	File saved_file = new File(realPath + "/" + relative_path.replaceAll(UPLOAD_DIR, SAVED_DIR));
    	copy(temp_file, saved_file);
    	return relative_path.replaceAll(UPLOAD_DIR, SAVED_DIR);
    }

    public boolean isTempFile(String relative_path) {
    	return relative_path.indexOf(UPLOAD_DIR) >= 0;
    }
 
    /**
     * From:
     * http://www.exampledepot.com/egs/java.nio/File2File.html
     * 
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copy(File src, File dst) throws IOException {
        // Create channel on the source
        FileChannel srcChannel = new FileInputStream(src.getCanonicalFile()).getChannel();
    
        // Create channel on the destination
        FileChannel dstChannel = new FileOutputStream(dst.getCanonicalFile()).getChannel();
    
        // Copy file contents from source to destination
        dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
    
        // Close the channels
        srcChannel.close();
        dstChannel.close();
    }
  
    
    @SuppressWarnings("unchecked")
	public static void getMultipartFileItemsAndParameters(final List<FileItem> items,
			final Map<String, FileItem> fileItems, final Map<String, Object> formItems) {
		for (FileItem item : items) {
			if (item.isFormField()) {
				// a bit of ugly if for example a <select multiple='multiple'>
				if (formItems.containsKey(item.getFieldName())) {
					Object values = formItems.get(item.getFieldName());
					if (values instanceof Collection == false) {
						String tmp = (String)values;
						values = new ArrayList<String>();
						formItems.put(item.getFieldName(), values);
						((Collection)values).add(tmp);
					}
					((Collection)values).add(item.getString());
				} else
					formItems.put(item.getFieldName(),	item.getString());
			} else {
				fileItems.put(item.getFieldName(),	item);
			}
		}
	}
    
    @SuppressWarnings("unchecked")
    public static List<FileItem> getMultipartFileItems(HttpServletRequest request) throws FileUploadException{
    	
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	factory.setRepository(new File(UPLOAD_DIR));
    	factory.setSizeThreshold(MAX_FILE_SIZE);
    	//DiskFileUpload fu = new DiskFileUpload(factory);
    	ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		items = servletFileUpload.parseRequest(request);
		return items;
	}
}
