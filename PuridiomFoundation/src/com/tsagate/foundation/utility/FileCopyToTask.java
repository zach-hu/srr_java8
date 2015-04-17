package com.tsagate.foundation.utility;

import java.io.File;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo <br>
 * set in incomingRequest the following:
 * <b><i>organizationId</i></b> - (String)Organization id
 * <b><i>originalFile</i></b> - (File) original File to copy from.
 * <b><i>newFile</i></b> - (String) File name to copy to.
 *
 * This class receives a file checks to see if the file is valid and obtains the new file
 * from emails.properties file.
 * Calls Foundation.Utility() to actually copy contents of the files.
 */
public class FileCopyToTask extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Log.debug(this, "DocumentFileCopyToTemp ");
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			File  originalFile = (File)incomingRequest.get("originalFile");
			String	originalPath = originalFile.getParent();

            if (Utility.isEmpty(originalPath))
            {
        		throw new TsaException("The base path was not specified.");
        	}

            File originaldir = new File(originalPath);
            if (!originaldir.isDirectory())
            {
                Log.error(this, "The base directory [" + originalPath + "] is not a valid directory.");
                throw new TsaException("The base directory document path is not a valid directory.");
            }
            if (!originaldir.canRead())
            {
                Log.error(this, "The base directory [" + originalPath + "] does not have read access.");
    			throw new TsaException("The document cannot be copied because the application does not have read access for the original document path.");
    		}
            if (!originaldir.canWrite()) {
                Log.error(this, "The base directory [" + originalPath + "] does not have write access.");
                throw new TsaException("The document cannot be copied because the application does not have write access for the original document path.");
            }

            if (originalPath.charAt(originalPath.length() - 1) != File.separatorChar)
            {
            	originalPath = originalPath + File.separator;
            }

    		//check for original file itself
    		if (!originalFile.exists())
    		{
                Log.error(this, "The original file does not exist [" + originalFile.getName() + "]");
    			throw new TsaException("The original file does not exist [" + originalFile.getName() + "]");
    		}
    		if (!originalFile.isFile())
    		{
    		    Log.error(this, "The original file is not valid [" + originalFile.toString() + "]");
    			throw new TsaException("The original file is not valid [" + originalFile.toString() + "]");
    		}
    		Log.debug(this, "FileCopy " + originalFile.getAbsolutePath());

    		String	newPath = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.basepath", "");
    		String	newFileName = (String) incomingRequest.get("newFile");
    		if(newFileName.indexOf(".EML") > -1)
    		{
    			//newFileName = newFileName.replaceAll(".EML", ".msg");
    		}
    		String getPath = (String)incomingRequest.get("getPath");
    		if(Utility.isEmpty(getPath))
    		{
    			getPath = "Y";
    		}
    		if(getPath.equalsIgnoreCase("Y"))
    		{
    			newFileName = newPath + newFileName;
    		}
            Log.debug(this, "New File name: " + newFileName);
            File newFile = new File(newFileName);
            File newDir = new File(newPath);
            if (!newDir.isDirectory())
            {
                Log.error(this, "The new directory [" + newPath + "] is not a valid directory.");
                throw new TsaException("The base directory document path is not a valid directory.");
            }
            if (!newDir.canRead())
            {
                Log.error(this, "The new directory [" + newPath + "] does not have read access.");
    			throw new TsaException("The document cannot be copied because the application does not have read access for the new document path.");
    		}
            if (!newDir.canWrite()) {
                Log.error(this, "The new directory [" + newPath + "] does not have write access.");
                throw new TsaException("The document cannot be copied because the application does not have write access for the new document path.");
            }

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

    		Utility.copyFile(originalFile, newFile);
    		Log.debug(this, "DocumentFileCopyToTemp newFilename: " + newFileName);

            result = newFileName;

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
		    //this.setStatus(Status.FAILED);
		    //throw e;
		    // Do not stop the process becuase the file could not be copied.
			Log.error(this, "File could not be copied to base directory");
		    return null;
		}

		return result;
	}
}