/**
 * Created on Feb 18, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardNotAwardedSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class PoForwardNotAwardedSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			poHeader.setAppDate(Dates.getDate(Dates.today("", poHeader.getTimeZone())));
			poHeader.setAppBy((String)incomingRequest.get("userId"));
			poHeader.setApproved("Y");

			String receiptRequired = poHeader.getReceiptRequired();
			if(!receiptRequired.equals("R") && !receiptRequired.equals("E"))
			{
			    //PO_ONORDER status is no longer being used
				//poHeader.setStatus(DocumentStatus.PO_ONORDER);
			}
			else
			{
				String oldStatus = (String)incomingRequest.get("oldStatus");
				Boolean revision = (Boolean)incomingRequest.get("revision");
				if(revision.booleanValue() && !oldStatus.equals(DocumentStatus.REQ_REJECTED))
				{
					poHeader.setStatus(oldStatus);
				}
				else
				{
					poHeader.setStatus(DocumentStatus.REQ_APPROVED);
				}
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return super.executeTask(object);
	}

}
