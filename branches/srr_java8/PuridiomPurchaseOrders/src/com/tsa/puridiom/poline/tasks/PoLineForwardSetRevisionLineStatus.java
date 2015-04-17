/**
 * Created on Feb 24, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoLineForwardSetRevisionLineStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineForwardSetRevisionLineStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List poLines = (List)incomingRequest.get("poLineList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			for (int i = 0; i < poLines.size(); i++)
			{
				PoLine poLine = (PoLine)poLines.get(i);
				incomingRequest.put("poLine", poLine);
				String lineReceiptRequired = poLine.getReceiptRequired();
				incomingRequest.put("lineReceiptRequired", lineReceiptRequired);
				String oldLineStatus = poLine.getStatus();
				incomingRequest.put("oldLineStatus", oldLineStatus);
				BigDecimal lineKey = poLine.getIcLineKey();
				incomingRequest.put("lineKey", lineKey);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("po-forward-setline-status.xml");
				process.executeProcess(incomingRequest);
				
				if(process.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("An error occurred setting status for line: " + i);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
