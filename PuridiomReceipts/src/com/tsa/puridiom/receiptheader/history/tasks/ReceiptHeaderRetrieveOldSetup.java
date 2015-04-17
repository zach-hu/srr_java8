package com.tsa.puridiom.receiptheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptHeaderRetrieveOldSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			ReceiptHeader newHeader = (ReceiptHeader)incomingRequest.get("newHistoryReceiptHeader");
			if(newHeader == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("New Receipt Header was not found!");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
