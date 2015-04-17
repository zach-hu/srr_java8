package com.tsa.puridiom.requisition.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class TransactionTypeListSetup extends Task{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			incomingRequest.put("StdTable_tableType", "RQ01");
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
			throw e;
		}
		return null;
	}
}
