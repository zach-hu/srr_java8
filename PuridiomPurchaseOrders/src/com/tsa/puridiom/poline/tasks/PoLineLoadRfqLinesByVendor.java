/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineLoadRfqLines.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoLineLoadRfqLinesByVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List lines = (List)incomingRequest.get("lines");
		List rfqLines = (List)incomingRequest.get("rfqLines");
		List rfqBids = (List)incomingRequest.get("rfqBids");
		List poLineList = new ArrayList();
		int line = 1;
		for (int i = 0; i < lines.size(); i++)
		{
			RfqLine rfqLine = (RfqLine) rfqLines.get(i);
			RfqBid rfqBid = (RfqBid)rfqBids.get(i);
			if(rfqLine.getStatus().equals(DocumentStatus.RFQ_PURCHASING))
			{
				incomingRequest.put("rfqLine", rfqLine);
				incomingRequest.put("rfqBid", rfqBid);
				incomingRequest.put("lineNumber", String.valueOf(line));
				if(incomingRequest.get("poLine") != null)
				{
					incomingRequest.put("poLine", null);
				}
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
	            PuridiomProcess process = processLoader.loadProcess("poline-load-from-rfq-by-vendor.xml");
	            process.executeProcess(incomingRequest);
				//int status = EntityUtility.executeProcess("poline-load-from-rfq-by-vendor.xml", incomingRequest);
				poLineList.add(incomingRequest.get("poLine"));
				line++;
				this.setStatus(Status.SUCCEEDED);
			}
		}
		incomingRequest.put("poLineList", poLineList);
		return null;
	}

}
