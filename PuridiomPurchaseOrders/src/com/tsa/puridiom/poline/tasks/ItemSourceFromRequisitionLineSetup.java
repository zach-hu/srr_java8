/**
 * Created on Mar 11, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.ItemSourceFromRequisitionLineSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ItemSourceFromRequisitionLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("reqLine");
			incomingRequest.put("catalogId", reqLine.getCatalogId());
			incomingRequest.put("RequisitionLine_itemSource", reqLine.getItemSource().substring(0, 2));
			
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
