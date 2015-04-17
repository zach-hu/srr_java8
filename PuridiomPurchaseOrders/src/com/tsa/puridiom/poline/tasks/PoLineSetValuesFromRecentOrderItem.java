/**
 *
 * Created on Jan 21, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineLookupSetValues.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoLineSetValuesFromRecentOrderItem extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String itemNumber = HiltonUtility.ckNull((String) incomingRequest.get("CatalogItem_itemNumber"));
			String itemDescription = HiltonUtility.ckNull((String) incomingRequest.get("RecentItem_description"));

			incomingRequest.put("PoLine_itemNumber", itemNumber);
			incomingRequest.put("PoLine_description", itemDescription);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			Log.error(this, e.toString());
		}
		return result;
	}
}