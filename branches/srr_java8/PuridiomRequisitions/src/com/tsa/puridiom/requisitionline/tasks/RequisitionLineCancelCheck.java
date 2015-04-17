/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelCheck.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineCancelCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			String lineStatus = requisitionLine.getStatus();
			
			String reqStatus = reqHeader.getStatus();
			if(reqStatus.equals(DocumentStatus.CANCELLED))
			{
			    this.setStatus(Status.FAILED);
				throw new TsaException("Requisition has Already been Cancelled!");
			}
			if(lineStatus.equals(DocumentStatus.CANCELLED))
			{
			    this.setStatus(Status.FAILED);
				throw new TsaException("Line has Already been Cancelled!");
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return super.executeTask(object);
	}

}