/**
 * Created on Mar 1, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdateAwardedStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineUpdateAwardedStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List poLines =(List)incomingRequest.get("poLineList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			for (int i = 0; i < poLines.size(); i++)
			{
				PoLine poLine = (PoLine)poLines.get(i);
				boolean revision = ((Boolean)incomingRequest.get("revision")).booleanValue();
				if(!revision)
				{
					String icRfqLine = Utility.tsaToString(poLine.getIcRfqLine());
					if(!Utility.isEmpty(icRfqLine))
					{	
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("update-rfq-vendor-award.xml");
						incomingRequest.put("vendor", poHeader.getVendorId());
						incomingRequest.put("poLine", poLine);
						incomingRequest.put("RfqLine_icRfqLine", icRfqLine);
						process.executeProcess(incomingRequest);
					}
				}
				String item = poLine.getItemNumber();
				//String itemSource = poLine.getItemSource();
				if(!Utility.isEmpty(item))
				{
					incomingRequest.put("InvItem_itemNumber", item);
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("poline-forward-update-inventory.xml");
					incomingRequest.put("poLine", poLine);
					process.executeProcess(incomingRequest);
				}
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poline-update-stock.xml");
				incomingRequest.put("InvItem_itemNumber", item);
				process.executeProcess(incomingRequest);
				
				processLoader = new PuridiomProcessLoader();
				process = processLoader.loadProcess("poline-update-prices.xml");
				incomingRequest.put("InvItem_itemNumber", item);
				process.executeProcess(incomingRequest);
				
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
