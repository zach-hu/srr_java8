package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionWirelessApproveSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			if(reqHeader != null)
			{
				ret = reqHeader.getIcReqHeader().toString();
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition could not be found!", e);
		}
		// TODO Auto-generated method stub
		return ret;
	}


}
