package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.ChecklistEntry;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ChecklistEntryRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			ChecklistEntry	checklistEntry = (ChecklistEntry) incomingRequest.get("checklistEntry");
			String	icQuestion = checklistEntry.getIcQuestion().toString();

			incomingRequest.put("ResponseValue_icQuestion", icQuestion);
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}