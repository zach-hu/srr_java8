/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineLoadRfqLines.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.EntityUtility;
import com.tsagate.foundation.utility.TsaException;

public class PoLineLoadRfqLines extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List lines = (List)incomingRequest.get("lines");
			List rfqLines = (List)incomingRequest.get("rfqLines");
			List rfqBids = (List)incomingRequest.get("rfqBids");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			int line = 1;
			for (int i = 0; i < lines.size(); i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLines.get(i);
				RfqBid rfqBid = (RfqBid)rfqBids.get(i);
				if(rfqLine.getStatus().equals(DocumentStatus.RFQ_PURCHASING))
				{
					rfqLine.setVendorAwarded(poHeader.getVendorId());
					incomingRequest.put("rfqLine", rfqLine);
					incomingRequest.put("rfqBid", rfqBid);
					incomingRequest.put("lineNumber", String.valueOf(line));
					if(incomingRequest.get("poLine") != null)
					{
						incomingRequest.put("poLine", null);
					}
					int status = EntityUtility.executeProcess("poline-load-from-rfq.xml", incomingRequest);
					if(status == Status.SUCCEEDED)
					{
						rfqLine.setStatus(DocumentStatus.PO_INPROGRESS);
					}
					line++;
					this.setStatus(status);
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An error ocurred loading rfq lines!", e);
		}
		return null;
	}

}
