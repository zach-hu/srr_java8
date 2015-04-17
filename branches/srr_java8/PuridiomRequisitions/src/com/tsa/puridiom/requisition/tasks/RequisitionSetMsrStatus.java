package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;

import java.util.Map;

public class RequisitionSetMsrStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			String newStatus = (String) incomingRequest.get("newStatus");
			if (newStatus == null) {
				newStatus = DocumentStatus.REQ_PLANNING_RECALLED;
			}

			RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			rqh.setStatus(newStatus);
			Log.debug(this, "Requisition " + rqh.getRequisitionNumber() + " newStatus is: " + newStatus);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occured recalling the current MSR ", e);
		}
		return null;
	}

}
