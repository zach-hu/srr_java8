package com.tsa.puridiom.documentfile.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class DocumentFileCopy extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
	        String	organizationId = (String) incomingRequest.get("organizationId");
	        String	filename = (String) incomingRequest.get("filename");
	        String	originalDocType = (String) incomingRequest.get("originalDocType");
	        String	newDocType = (String) incomingRequest.get("newDocType");
	        
	        if (Utility.isEmpty(filename)) {
	            filename = (String) incomingRequest.get("DocAttachment_docFilename");
	            if (Utility.isEmpty(filename)) {
	                throw new Exception("The filename was not specified.");
	            }
	        }
	        if (Utility.isEmpty(originalDocType)) {
	            originalDocType = "internal";
	        }
	        if (Utility.isEmpty(newDocType)) {
	            newDocType = "internal";
	        }
	        
	        String	originalDirPath = DictionaryManager.getInstance("host", organizationId).getProperty(originalDocType + "-document-path", "/");
	        String	newDirPath = DictionaryManager.getInstance("host", organizationId).getProperty(newDocType + "-document-path", "/");
	        
            if (Utility.isEmpty(originalDirPath)) {
        		throw new Exception("The original document path [" + originalDocType + "-document-path] was not specified.");
        	}
            if (Utility.isEmpty(newDirPath)) {
        		throw new Exception("The new document path [" + newDocType + "-document-path] was not specified.");
        	}
            
            File originalDir = new File(originalDirPath);
            if (!originalDir.isDirectory()) {
                Log.error(this, "The " + originalDocType + "-document-path [" + originalDirPath + "] is not a valid directory.");
                throw new IOException("The " + originalDocType + " document path is not a valid directory.");
            }
            if (!originalDir.canRead()) {
                Log.error(this, "The " + originalDocType + "-document-path [" + originalDirPath + "] does not have read access.");
    			throw new IOException("The document cannot be copied because the application does not have read access for the " + originalDocType + " document path.");
    		}

            if (originalDirPath.charAt(originalDirPath.length() - 1) != File.separatorChar) {
                originalDirPath = originalDirPath + File.separator;
            }
            
    		File origFile = new File(originalDirPath + filename);
    		if (!origFile.exists()) {
                Log.error(this, "The original file does not exist [" + origFile.toString() + "]");
    			throw new IOException("The original file does not exist [" + filename + "]");
    		}
    		if (!origFile.isFile()) {
    		    Log.error(this, "The original file is not valid [" + origFile.toString() + "]");
    			throw new IOException("The original file is not valid [" + filename + "]");
    		}
            
            File newDir = new File(newDirPath);
            if (!newDir.isDirectory()) {
                Log.error(this, "The " + newDocType + "-document-path [" + newDirPath + "] is not a valid directory.");
                throw new IOException("The " + newDocType + " document path is not a valid directory.");
            }
            if (!newDir.canWrite()) {
                Log.error(this, "The " + newDocType + "-document-path [" + newDirPath + "] does not have write access.");
                throw new IOException("The document cannot be copied because the application does not have write access for the " + newDocType + " document path.");
            }

            if (newDirPath.charAt(newDirPath.length() - 1) != File.separatorChar) {
                newDirPath = newDirPath + File.separator;
            }
    		
    		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String	newFilename = ukg.getUniqueKey().toString() + filename.substring(filename.lastIndexOf("."));
    		File newFile = new File(newDirPath + newFilename);
            
    		FileInputStream from = null;
    		FileOutputStream to = null;
    		try
    		{
    			from = new FileInputStream(origFile);
    			to = new FileOutputStream(newFile);
    			byte buffer[] = new byte[from.available()];
    			int bytesRead;
    			
    			while ((bytesRead = from.read(buffer)) > -1) {
    				to.write(buffer, 0, bytesRead);
    			}
    			if (!newFile.exists()) {
    				throw new IOException("The file could not be created.");
    			}
    		}
    		finally {
    			if (from != null) {	
    				try {
    					from.close();
    				}
    				catch (IOException e) {
    				}
    			}
    			if (to != null) {
    				try {
    					to.close();
    				}
    				catch (IOException e) {
    				}
    			}
    		}
    		
    		incomingRequest.put("newDocAttachment_docFilename", newFilename);
            result = newFilename;
            
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
		    //this.setStatus(Status.FAILED);
		    //throw e;
		    // Do not stop the process becuase the file could not be copied.
		    return null;
		}
		
		return result;
	}
}