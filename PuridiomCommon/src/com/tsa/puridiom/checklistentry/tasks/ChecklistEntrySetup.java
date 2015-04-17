package com.tsa.puridiom.checklistentry.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class ChecklistEntrySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("ChecklistEntry_icQuestion", ukg.getUniqueKey().toString());
			incomingRequest.put("ChecklistEntry_sequence", "0");
			incomingRequest.put("ChecklistEntry_questionText", "");
			incomingRequest.put("ChecklistEntry_responseType", "");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}