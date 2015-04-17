package com.tsa.puridiom.po.notifications.tasks;

import com.tsa.puridiom.entity.PoHeader;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Map;

public class SetMessageErrorPoDuplicate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeaderDuplicate");
			String poNumber = "";
			String reqNumber = "";
			if (poHeader != null)
			{
				poNumber = poHeader.getPoNumber();
				reqNumber = poHeader.getRequisitionNumber();
			}
			incomingRequest.put("errorMessagePoNumber", poNumber);
			incomingRequest.put("errorMessageReqNumber", reqNumber);
			incomingRequest.put("successPage", "/orders/po_duplicate.jsp");

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