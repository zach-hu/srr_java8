package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class InvReturnSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvReturn_requisitionNumber", "");
			incomingRequest.put("InvReturn_disbNumber", "");
			incomingRequest.put("InvReturn_lineNo", "0");
			incomingRequest.put("InvReturn_itemNumber", "");
			incomingRequest.put("InvReturn_icReqHeader", "0");
			incomingRequest.put("InvReturn_icReqLine", "0");
			incomingRequest.put("InvReturn_icDsbHeader", "0");
			incomingRequest.put("InvReturn_icDsbLine", "0");
			incomingRequest.put("InvReturn_icBin", "0");
			incomingRequest.put("InvReturn_recBy", "");
			incomingRequest.put("InvReturn_recDate", Dates.today("", ""));
			incomingRequest.put("InvReturn_recAmount", "0");
			incomingRequest.put("InvReturn_owner", "");
			incomingRequest.put("InvReturn_duomQty", "0");

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