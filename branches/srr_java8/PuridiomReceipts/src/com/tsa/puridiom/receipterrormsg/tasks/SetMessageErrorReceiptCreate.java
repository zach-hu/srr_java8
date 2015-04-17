package com.tsa.puridiom.receipterrormsg.tasks;

import com.tsa.puridiom.entity.PoHeader;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Map;

public class SetMessageErrorReceiptCreate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String poNumber = "";
			String poStatus = "";
			if (poHeader != null)
			{
				poNumber = poHeader.getPoNumber();
				poStatus = poHeader.getStatus();
			}
			incomingRequest.put("errorMessagePoNumber", poNumber);
			incomingRequest.put("errorMessagePoStatus", poStatus);
			incomingRequest.put("successPage", "/receipts/rec_error_message.jsp");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.debug(this, "Error SetMessageErrorPoDuplicate: \r\n" + e.getMessage());
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}