package com.tsa.puridiom.documentfile.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class DocumentFileCopyToTemp extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Log.debug(this, "DocumentFileCopyToTemp ");
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			DocAttachment	attachment = (DocAttachment) incomingRequest.get("docAttachment");
	        String	originalPath = DictionaryManager.getInstance("host", organizationId).getProperty("hs-internal-document-path", "");
	        if(Utility.isEmpty(originalPath))
	        {
	        	originalPath = DictionaryManager.getInstance("host", organizationId).getProperty("internal-document-path", "/");
	        }

            if (Utility.isEmpty(originalPath))
            {
        		throw new TsaException("The internal document path was not specified.");
        	}

            File originaldir = new File(originalPath);
            if (!originaldir.isDirectory())
            {
                Log.error(this, "The internal-document-path [" + originalPath + "] is not a valid directory.");
                throw new TsaException("The internal document path is not a valid directory.");
            }
            if (!originaldir.canRead())
            {
                Log.error(this, "The internal-document-path [" + originalPath + "] does not have read access.");
    			throw new TsaException("The document cannot be copied because the application does not have read access for the internal document path.");
    		}
            if (!originaldir.canWrite()) {
                Log.error(this, "The internal-document-path [" + originalPath + "] does not have write access.");
                throw new TsaException("The document cannot be copied because the application does not have write access for the internal document path.");
            }

            if (originalPath.charAt(originalPath.length() - 1) != File.separatorChar)
            {
            	originalPath = originalPath + File.separator;
            }

    		File origFile = new File(originalPath + attachment.getDocFilename());
    		if (!origFile.exists())
    		{
                Log.error(this, "The original file does not exist [" + origFile.toString() + "]");
    			throw new TsaException("The original file does not exist [" + attachment.getDocFilename() + "]");
    		}
    		if (!origFile.isFile())
    		{
    		    Log.error(this, "The original file is not valid [" + origFile.toString() + "]");
    			throw new TsaException("The original file is not valid [" + attachment.getDocFilename() + "]");
    		}
    		Log.debug(this, "DocumentFileCopyToTemp " + origFile.getAbsolutePath());
    		incomingRequest.put("DocAttachment_docFilename", origFile.getAbsolutePath());

    		String	tempPath = DictionaryManager.getInstance("host", organizationId).getProperty("hs-temporary-document-path", "");
    		if(HiltonUtility.isEmpty(tempPath)) {		tempPath = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-path", "/");	}

            String	newFilename = tempPath + File.separator + HiltonUtility.parseFilename(attachment.getDocTitle());
            String originalExtension = attachment.getDocFilename().substring(attachment.getDocFilename().lastIndexOf("."));
        	if(newFilename.lastIndexOf(originalExtension) < 0)
        	{
        		newFilename = newFilename + originalExtension;
        	}

    		File newFile = new File(newFilename);

    		if(!newFile.exists())
    		{
    			newFile.createNewFile();
    		}
    		else
    		{
    			if(newFile.delete())
    			{
    				newFile.createNewFile();
    			}
    		}

    		FileInputStream from = null;
    		FileOutputStream to = null;
    		try
    		{
    			from = new FileInputStream(origFile);
    			to = new FileOutputStream(newFile);
    			byte buffer[] = new byte[from.available()];
    			int bytesRead;

    			while ((bytesRead = from.read(buffer)) > -1)
    			{
    				to.write(buffer, 0, bytesRead);
    			}
    			if (!newFile.exists())
    			{
    				throw new IOException("The file could not be created.");
    			}
    		}
    		finally
    		{
    			if (from != null)
    			{
    				try
    				{
    					from.close();
    				}
    				catch (IOException e)
    				{
    				}
    			}
    			if (to != null)
    			{
    				try
    				{
    					to.close();
    				}
    				catch (IOException e)
    				{
    				}
    			}
    		}

    		incomingRequest.put("newDocAttachment_docFilename", newFilename);
    		Log.debug(this, "DocumentFileCopyToTemp newFilename: " + newFilename);

            result = newFilename;

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
		    //this.setStatus(Status.FAILED);
		    //throw e;
		    // Do not stop the process becuase the file could not be copied.
		    return null;
		}

		return result;
	}
}