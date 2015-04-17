/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrievelastByNumber.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoSetupNewLastRevision extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			//poHeader.setLastRevision("C");
			//poHeader.setStatus(DocumentStatus.PO_AWARDED);

			String icPoHeader = poHeader.getIcPoHeader().toString();
			incomingRequest.put("PoHeader_icPoHeader", icPoHeader);
			incomingRequest.put("PoLine_icPoHeader", icPoHeader);

			result = poHeader;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
