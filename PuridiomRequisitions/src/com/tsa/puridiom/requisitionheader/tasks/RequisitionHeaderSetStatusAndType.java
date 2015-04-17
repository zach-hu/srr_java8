package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionHeaderSetStatusAndType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			if (rqh != null)
			{
				rqh.setRequisitionType(RequisitionType.PURCHASE_REQUEST);
				rqh.setStatus(DocumentStatus.REQ_INPROGRESS);
				incomingRequest.put("requisitionHeader", rqh);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}