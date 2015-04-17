/**
 *
 * Created on Jan 21, 2004
 * @author kathleen
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineSetValuesFromRecentRequestedItem.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class RequisitionLineSetValuesFromRecentRequestedItem extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String itemNumber = HiltonUtility.ckNull((String) incomingRequest.get("CatalogItem_itemNumber"));
			String itemDescription = HiltonUtility.ckNull((String) incomingRequest.get("RecentItem_description"));

			incomingRequest.put("RequisitionLine_itemNumber", itemNumber);
			incomingRequest.put("RequisitionLine_description", itemDescription);

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