package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionHistorySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");

			incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
			incomingRequest.put("ApprovalLog_icHeader", icHeader);
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition History encountered an Error. ", e);
		}


		return ret;
	}

}
