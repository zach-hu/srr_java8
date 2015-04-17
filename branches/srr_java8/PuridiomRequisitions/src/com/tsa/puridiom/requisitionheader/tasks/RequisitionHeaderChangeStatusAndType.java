package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RequisitionHeaderChangeStatusAndType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String newReqType = (String) incomingRequest.get("newReqType");
			if (rqh != null)
			{
				if (rqh.getRequisitionType().equals("E") && (newReqType.equals("P")||newReqType.equals("N")))
				{
					rqh.setIcRevisedOrder(new BigDecimal(0));
				}
				rqh.setRequisitionType(newReqType);
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