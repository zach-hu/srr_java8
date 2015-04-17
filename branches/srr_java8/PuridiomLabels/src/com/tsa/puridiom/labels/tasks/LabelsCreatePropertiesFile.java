package com.tsa.puridiom.labels.tasks;

import java.io.File;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class LabelsCreatePropertiesFile extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Log.debug(this, "LabelsCreatePropertiesFile");
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
	        String	path = DictionaryManager.getInstance("host", organizationId).getProperty("properties-path", "properties.labels");

	        path = path + organizationId.toLowerCase();

            if (Utility.isEmpty(path))
            {
        		throw new TsaException("The properties-path was not specified.");
        	}

            File directory = new File(path);
            if (!directory.isDirectory())
            {
                Log.error(this, "The properties-path [" + path + "] is not a valid directory.");
                throw new TsaException("The properties path is not a valid directory.");
            }
            if (!directory.canRead())
            {
                Log.error(this, "The properties-path [" + path + "] does not have read access.");
    			throw new TsaException("The labels properties file cannot be created because the application does not have read access for the properties path.");
    		}
            if (!directory.canWrite()) {
                Log.error(this, "The properties-path [" + path + "] does not have write access.");
                throw new TsaException("The labels properties file cannot be created because the application does not have write access for the properties path.");
            }

            if (path.charAt(path.length() - 1) != File.separatorChar)
            {
            	path = path + File.separator;
            }

            String	newFilename = path + "labels.properties";
    		File file = new File(newFilename);

    		if(!file.exists())
    		{
    			file.createNewFile();
    		}
    		else
    		{
    			if(file.delete())
        		{
        			file.createNewFile();
        		}
    		}

    		result = file;

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.getMessage());
		    this.setStatus(Status.FAILED);
		}

		return result;
	}
}