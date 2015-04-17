package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionRejectByBuyerSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			if(Utility.isEmpty(icHeader))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Requistion was not found!");
			}
			incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
			incomingRequest.put("ApprovalLog_icHeader", icHeader);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requistion could not be Rejected!", e);
		}
		return ret;
	}


}
