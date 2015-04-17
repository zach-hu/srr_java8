package com.tsa.puridiom.requisition.tasks;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionRejectByBuyer extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			String user = (String)incomingRequest.get("userId");
			incomingRequest.put("rejectedBy", user);
			incomingRequest.put("newStatus", DocumentStatus.REQ_REJECTED);

			List lines = (List)incomingRequest.get("requisitionLineList");

			header.setStatus(DocumentStatus.REQ_REJECTED);
			header.setLastChgBy(user);
			header.setLastChgDate(Calendar.getInstance().getTime());

			for (Iterator iter = lines.iterator(); iter.hasNext();)
			{
				RequisitionLine line = (RequisitionLine) iter.next();
				line.setStatus(DocumentStatus.REQ_REJECTED);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition status could not be set to rejected!", e);
		}
		return ret;
	}
}
