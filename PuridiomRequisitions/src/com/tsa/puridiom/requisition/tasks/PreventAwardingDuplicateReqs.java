package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PreventAwardingDuplicateReqs extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			if(!rqh.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
			{
				this.setPostAction("exitProcess");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Duplicate Orders were found!");
		}
		return super.executeTask(object);
	}


}
