/**
 * Created on Feb 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardOptionAward.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoForwardOptionAward extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			poHeader.setAppBy((String)incomingRequest.get("userId"));
			poHeader.setAppSigned("N");
			poHeader.setApproved("Y");
			poHeader.setStatus(DocumentStatus.PO_AWARDED);

/*
			If the order is being awarded the header status should always be PO_AWARDED,
			unless items have been previously received or listed as no receipt required.
			Therefore, the following code is not necessary:
			
			String receiptRequired = poHeader.getReceiptRequired();
			if (!receiptRequired.equals("R") && !receiptRequired.equals("E"))
			{
				poHeader.setStatus(DocumentStatus.PO_AWARDED);
			}
			else
			{
				Boolean revision = (Boolean)incomingRequest.get("revision");
				String oldStatus = (String)incomingRequest.get("oldStatus");
				if(revision.booleanValue() && oldStatus.equals(DocumentStatus.PO_REJECTED))
				{
					poHeader.setStatus(oldStatus);
				}
				else
				{
					poHeader.setStatus(DocumentStatus.PO_AWARDED);
				}
			}
*/
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
