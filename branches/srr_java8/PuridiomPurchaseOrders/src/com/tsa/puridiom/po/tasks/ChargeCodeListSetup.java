package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ChargeCodeListSetup extends Task{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			incomingRequest.put("StdTable_tableType", "PO10");
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
			throw e;
		}
		return null;
	}
}

