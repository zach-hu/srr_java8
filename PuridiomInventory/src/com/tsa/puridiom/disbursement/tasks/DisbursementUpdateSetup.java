/*
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryUpdateDisbursementSetup.java
 */
 
package com.tsa.puridiom.disbursement.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbursementUpdateSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String oldQtyString = (String)incomingRequest.get("old_DisbLine_qty");
			BigDecimal oldQty = new BigDecimal(oldQtyString);
			String newQtyString = (String)incomingRequest.get("DisbLine_quantity");
			BigDecimal newQty = new BigDecimal(newQtyString);
			BigDecimal diff = oldQty.subtract(newQty);
			incomingRequest.put("diff", diff);
			
			String itemLocation = (String) incomingRequest.get("DisbLine_itemLocation");
			String itemNumber = (String) incomingRequest.get("DisbLine_itemNumber");
			String icRcString = (String)incomingRequest.get("DisbLine_icRc");
			String icReqLine = (String)incomingRequest.get("DisbLine_icReqLine");
			
			incomingRequest.put("InvLocation_itemNumber", itemNumber);
			incomingRequest.put("InvLocation_itemLocation", itemLocation);
			incomingRequest.put("InvBinLocation_icRc", icRcString);
			incomingRequest.put("RequistionLine_icReqLine", icReqLine);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null;
	}
}