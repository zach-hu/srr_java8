package com.tsa.puridiom.documentfile.tasks;

import com.oreilly.servlet.MultipartRequest;
//import com.tsa.puridiom.bidboard.handlers.IHandler;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.tsa.puridiom.property.*;
import com.tsa.puridiom.usermanager.UserManager;
//import com.tsa.puridiom.usermanager.*;
import com.tsa.puridiom.entity.*;

/*
 * Task to handle file uploads using MultipartRequest for decoding the incoming multipart/form-data stream
 */

public class XlsRfqReqPoUploadItems extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    HttpServletRequest request = (HttpServletRequest) incomingRequest.get("request");
            String incomingString = (String) request.getQueryString();
            String incomingArray[]= incomingString.split(";");

            String	organizationId = incomingArray[0];
            String formType = incomingArray[1];
            String icHeader = incomingArray[2];
            String userI = incomingArray[3];

            if (Utility.isEmpty(organizationId)) {
                throw new Exception("The organization id was not found.");
            }
            String	documentType = (String) incomingRequest.get("documentType");
            if (Utility.isEmpty(documentType)) {
        		documentType = "internal-document-path";
        	}
            String	dirName = DictionaryManager.getInstance("host", organizationId).getProperty(documentType, "");
            if (Utility.isEmpty(dirName)) {
        		throw new Exception("The " + documentType + " was not specified.");
        	}

            MultipartRequest multi = new MultipartRequest(request, dirName, 10*1024*1024); // 10MB
            Enumeration params = multi.getParameterNames();
            Enumeration files = multi.getFileNames();

            while (params.hasMoreElements()) {
                String name = (String)params.nextElement();
                String value = multi.getParameter(name);

                if (name.equalsIgnoreCase("successPage")) {
                    incomingRequest.put("successPage", value);
                }
                else if (name.equalsIgnoreCase("failurePage")) {
                    incomingRequest.put("failurePage", value);
                }

                request.setAttribute(name, value);
                incomingRequest.put(name, value);
            }

            if (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                String filename = multi.getFilesystemName(name);
                String type = multi.getContentType(name);
                File f = multi.getFile(name);

                if (f != null)
                {

                    File dir = new File(dirName);
                    if (!dir.isDirectory()) {
                        Log.error(this, "The " + documentType + " [" + dirName + "] is not a valid directory.");
                        throw new IOException("The " + documentType + " is not a valid directory.");
                    }
                    if (!dir.canWrite()) {
                        Log.error(this, "The " + documentType + " [" + dirName + "] does not have write access.");
                        throw new IOException("The application does not have write access for the " + documentType + ".");
                    }

                    if (dirName.charAt(dirName.length() - 1) != File.separatorChar) {
                        dirName = dirName + File.separator;
                    }

                    if(f.length() == 0 )
                    {
                    	throw new TsaException("Files can not be empty");
                    }

                    String	fileExtension = filename.substring(filename.lastIndexOf("."));
                    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
                    String	fileToName = ukg.getUniqueKey().toString() + fileExtension;

                    File nameto = new File(dirName + fileToName);
                    f.renameTo(nameto);

                    incomingRequest.put("filename", fileToName);
                    this.RfqReqPoAddItems(organizationId, formType, icHeader, fileToName, userI);
                    result = nameto;
                }
            }

            this.status = Status.SUCCEEDED;
        }
        catch (IOException lEx) {
            throw new Exception(lEx);
        } catch (Exception e) {
            throw e;
        }

		return result;
	}


	public void RfqReqPoAddItems(String organizationID, String formType, String icHeader, String fileName, String userI) {

    	try {

    		Map incomingRequest = new HashMap();

    	    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationID);
      	    PuridiomProcess process = processLoader.loadProcess("rfq-req-po-add-items.xml");

      	    incomingRequest.put("organizationId", organizationID);
      	    incomingRequest.put("icHeaderValue",icHeader);
        	incomingRequest.put("FilenameXls",fileName);
        	incomingRequest.put("formType",formType);

        	process.executeProcess(incomingRequest);

            this.setStatus(process.getStatus());

    	  }

    	  catch (Exception e)
    	  {
    		e.printStackTrace();

    	  }
       }

}