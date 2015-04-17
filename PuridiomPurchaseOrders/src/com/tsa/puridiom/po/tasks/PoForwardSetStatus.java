/**
 * Created on Feb 24, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardSetStatus.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoForwardSetStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			if(poHeader.getStatus().compareTo(DocumentStatus.PO_AWARDED) > -1 && poHeader.getStatus().equals(DocumentStatus.PO_REJECTED))
			{
				poHeader.setLockStatus("U");
			}
			if(poHeader.getStatus().equals(DocumentStatus.RCV_RECEIVED))
			{
				Boolean revision = (Boolean)incomingRequest.get("revision");
				if(revision.booleanValue() && (poHeader.getReceiptRequired().equals("R") || poHeader.getReceiptRequired().equals("E")))
				{
					poHeader.setLockStatus("U");
				}
			}
			incomingRequest.put("poHeader", poHeader);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
