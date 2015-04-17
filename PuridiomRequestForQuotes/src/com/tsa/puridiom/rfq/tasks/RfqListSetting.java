/**
 *
 * Created on Feb 4, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.GetBidEntities.java
 *
 */
package com.tsa.puridiom.rfq.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RfqListSetting extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = new Object();
		List bidLines = (ArrayList)incomingRequest.get("lines");
		List rfqHeaderList = new ArrayList();

		for (Iterator iter = bidLines.iterator(); iter.hasNext();)
		{
			Object o[] = (Object[])iter.next();
			rfqHeaderList.add(o[1]);
		}
		ret = rfqHeaderList;

		this.setStatus(Status.SUCCEEDED);
		return ret;
	}

}
