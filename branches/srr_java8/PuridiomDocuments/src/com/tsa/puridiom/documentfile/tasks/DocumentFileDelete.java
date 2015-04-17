package com.tsa.puridiom.documentfile.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DocumentFileDelete extends Task {
    
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
	        boolean success;
	        String	organizationId = (String) incomingRequest.get("organizationId");
	        String	filename = (String) incomingRequest.get("filename");
	        String	documentType = (String) incomingRequest.get("documentType");

			if (Utility.isEmpty(documentType)) {
        		documentType = "internal";
        	}
	        String	dirName = DictionaryManager.getInstance("host", organizationId).getProperty(documentType + "-document-path", "/");
            if (Utility.isEmpty(dirName)) {
        		throw new Exception("The " + documentType + " document path was not specified.");
        	}
	        
            File dir = new File(dirName);
            if (!dir.isDirectory()) {
                Log.error(this, "The " + documentType + "-document-path [" + dirName + "] is not a valid directory.");
                throw new IOException("The " + documentType + " document path is not a valid directory.");
            }
            if (!dir.canWrite()) {
                Log.error(this, "The " + documentType + "-document-path [" + dirName + "] does not have write access.");
                throw new IOException("The document cannot be deleted because the application does not have write access for the " + documentType + " document path.");
            }

            if (dirName.charAt(dirName.length() - 1) != File.separatorChar) {
                dirName = dirName + File.separator;
            }
            
            File filetodelete = new File(dirName + filename);
	        if (filetodelete.exists()) {	
	            success = filetodelete.delete();
	        }
	        
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
		    this.setStatus(Status.FAILED);
		    throw e;
		}
		
		return null;
	}

}