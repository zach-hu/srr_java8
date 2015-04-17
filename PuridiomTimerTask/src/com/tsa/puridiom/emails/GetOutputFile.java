package com.tsa.puridiom.emails;

import java.io.File;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class GetOutputFile extends Task
{
	private String organizationId = "CTBTO";

	public File outputFile(String from, String to, int success)
    {
//    	this.moveTo(filePath, success, (String)returned[1]);
    	File fileToMove = new File(from);
    	File parentDir = fileToMove.getParentFile().getParentFile();
    	File outputFile = null;
    	String name = fileToMove.getName();

    	String outputDir = parentDir.getAbsolutePath() + File.separator;
    	if(success == Status.SUCCEEDED)
    	{//move to processed
    		String attached = DictionaryManager.getInstance("emails", this.organizationId).getProperty("mail.filepath", "");
    		outputFile = new File(attached + "attached" + File.separator + to);
    		Log.warn(this, name + " " + to + " " + "Y");
    	}
    	else
    	{//move to failed
    		outputFile = new File(outputDir + "failed" + File.separator + name);
    		Log.warn(this, name + " " + to + " " + "N");
    	}
    	return outputFile;
    }

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			String from = (String)incomingRequest.get("from");
			String oid = (String)incomingRequest.get("organizationId");
			String to = (String)incomingRequest.get("to");
			Integer isuccess = (Integer)incomingRequest.get("successProcess");
			this.organizationId = oid;
			ret = this.outputFile(from, to, isuccess.intValue());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException();
		}
		return ret;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
}
