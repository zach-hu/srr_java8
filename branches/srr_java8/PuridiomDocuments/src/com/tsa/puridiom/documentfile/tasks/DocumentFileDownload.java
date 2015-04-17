package com.tsa.puridiom.documentfile.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletException;

/**
 * @author kelli
 * Task to handle file uploads using MultipartRequest for decoding the incoming multipart/form-data stream
 */
public class DocumentFileDownload extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
            String	organizationId = (String) incomingRequest.get("organizationId");
            String userId = (String)  incomingRequest.get("userId");
			String fileName = (String) incomingRequest.get("filename");
			String	documentType = (String) incomingRequest.get("documentType");
            
            if (Utility.isEmpty(organizationId)) {
                throw new ServletException("The selected file cannot be accessed without a valid organization id.");
            }
            if (Utility.isEmpty(userId)) {
				throw new ServletException("The selected file cannot be accessed without a valid user id.");
            }
			if (Utility.isEmpty(fileName)) {
				throw new IOException("The file name has not been specified.");
			}
			if (Utility.isEmpty(documentType)) {
        		documentType = "internal";
        	}
			
		    String originalPath = DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-path", "/");
			String tempPath = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-path", "/");
			File tempFile = null;
			
			if (!Utility.isEmpty(originalPath)) {
				tempFile = getTempFileFromDirectory(fileName,  documentType, originalPath, tempPath);
			}
			else {
				String protocol = DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-urlprotocol", "/");
				String host = DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-urlhost", "/");
				String path = DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-urlpath", "/");
				int		port = (Integer.valueOf(DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-urlport", "/"))).intValue();
				
				tempFile = getTempFileFromUrl(protocol, host, port, path, fileName, tempPath);
			}

			String tempUrl = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-url", "/");

			StringBuffer hostDocuments = new StringBuffer();
			Map applications = PropertiesManager.getInstance(organizationId).getSection("APPLICATION");
			String url = (String)applications.get("URL");
			String [] parts = url.split("/");
			hostDocuments.append(parts[0]); hostDocuments.append("//"); hostDocuments.append(parts[2]);
			parts = tempUrl.split("/");
			for (int a=3; a<parts.length; a++)
			{
				hostDocuments.append("/"); hostDocuments.append(parts[a]);
			}
			tempUrl = hostDocuments.toString();

			if (tempUrl.lastIndexOf("/") != tempUrl.length()) {	
				tempUrl = tempUrl + "/";
			}
            
			result = tempUrl + fileName;
			
            this.status = Status.SUCCEEDED;
        }
        catch (IOException lEx) {
            throw new Exception(lEx);
        } catch (Exception e) {
            throw e;
        }
		
		return result;
	}
	
	private File getTempFileFromDirectory(String fileName,String documentType, String dirName,String tempDirName)
	throws IOException
{
	File tempFile = null;
	File origDir = new File(dirName);
	File tempDir = new File(tempDirName);
	if (!origDir.isDirectory()) {
	    Log.error(this, "The " + documentType + "-document-path [" + dirName + "] is not a valid directory.");
		throw new IOException("The value specified for the  " + documentType + "-document-path is not a valid directory.");
	}
	if (!origDir.canRead()) {
	    Log.error(this, "The " + documentType + "-document-path [" + dirName + "] does not have read access.");
		throw new IOException("The the " + documentType + "-document-path does not have read access.");
	}
	if (!tempDir.isDirectory()) {
	    Log.error(this, "The temporary-document-path [" + dirName + "] is not a valid directory.");
		throw new IOException("The the temporary-document-path is not a valid directory.");
	}
	if (!tempDir.canWrite()) {
	    Log.error(this, "The temporary-document-path [" + dirName + "] does not have read access.");
		throw new IOException("The the temporary-document-path does not have read access.");
	}
	
	File origFile = new File(dirName + File.separator+ fileName);
	tempFile = new File(tempDirName + File.separator + fileName);
	if (!origFile.exists()) {
	    Log.error(this, "The selected file [" + origFile.toString() + "] does not exist.");
		throw new IOException("The selected file does not exist.");
	}
	if (!origFile.isFile()) {
	    Log.error(this, "The selected file [" + origFile.toString() + "] is not valid.");
		throw new IOException("The selected file is not valid.");
	}
	FileInputStream from = null;
	FileOutputStream to = null;
	try
	{
		from = new FileInputStream(origFile);
		to = new FileOutputStream(tempFile);
		byte buffer[] = new byte[from.available()];
		int bytesRead;
		
		while ((bytesRead = from.read(buffer)) > -1) {
			to.write(buffer, 0, bytesRead);
		}
		if (!tempFile.exists()) {
			throw new IOException("The temporary file could not be created.");
		}
	}
	finally
	{
		if (from != null) {	
			try {
				from.close();
			}
			catch (IOException e) {
				//getServletContext().log(e, "Error closing FileOutPutStream ");
			}
		}
		if (to != null) {
			try {
				to.close();
			}
			catch (IOException e) {
				//getServletContext().log(e, "Error closing FileOutPutStream");
			}
		}
	}
	return tempFile;
}
private File getTempFileFromUrl(String protocol,String host, int port,String path,String fileName,String tempDirName)
{
	File tempFile = new File(tempDirName + File.separator + "temp");
	DataInputStream from = null;
	FileOutputStream to = null;
	try
	{
	    URL u = new URL(protocol, host, port, path + fileName);
		from = new DataInputStream(u.openStream());
		to = new FileOutputStream(tempFile);
		byte buffer[] = new byte[from.available()];
		int bytesRead;

		while ((bytesRead = from.read(buffer)) > -1) {
			to.write(buffer, 0, bytesRead);
		}
		if (!tempFile.exists()) {
			throw new IOException("The temporary file could not be created.");
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		if (from != null) {
			try {
				from.close();
			}
			catch (IOException e) {
				//getServletContext().log(e, "Error closing FileOutPutStream ");
			}
		}
		if (to != null) {
			try {
				to.close();
			}
			catch (IOException e) {
				//getServletContext().log(e, "Error closing FileOutPutStream");
			}
		}
	}
	return tempFile;
}
}