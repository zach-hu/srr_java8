/*
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryUpdateDisbursementSetup.java
 */

package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class DisbLineUpdateSetup extends Task
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
			BigDecimal oldQty = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(oldQtyString))
				oldQty = new BigDecimal(oldQtyString);
			String newQtyString = (String)incomingRequest.get("DisbLine_quantity");
			BigDecimal newQty = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(newQtyString))
				newQty = new BigDecimal(newQtyString);
			BigDecimal diff = oldQty.subtract(newQty);

			String oldDuomQtyString = (String)incomingRequest.get("old_DisbLine_duomqty");
			BigDecimal oldDuomQty = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(oldDuomQtyString))
				oldDuomQty = new BigDecimal(oldDuomQtyString);
			String newDuomQtyString = (String)incomingRequest.get("DisbLine_duomQuantity");
			BigDecimal newDuomQty = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(newDuomQtyString))
				newDuomQty = new BigDecimal(newDuomQtyString);
			BigDecimal duomdiff = oldDuomQty.subtract(newDuomQty);

			String itemLocation = (String) incomingRequest.get("DisbLine_itemLocation");
			String itemNumber = (String) incomingRequest.get("DisbLine_itemNumber");
			String icRcString = (String)incomingRequest.get("DisbLine_icRc");
			String icReqLine = (String)incomingRequest.get("DisbLine_icReqLine");
			if(HiltonUtility.isEmpty(icReqLine))
				icReqLine = "0";

			String dsbNumber = (String) incomingRequest.get("DisbHeader_disbNumber") ;
			if (! (HiltonUtility.isEmpty(dsbNumber) || dsbNumber.equalsIgnoreCase("N/A"))) {
				incomingRequest.put("diff", diff);
				incomingRequest.put("duomdiff", duomdiff);
			} else {
				incomingRequest.put("diff", new BigDecimal(0));
				incomingRequest.put("duomdiff", new BigDecimal(0));
			}
			incomingRequest.put("InvLocation_itemNumber", itemNumber);
			incomingRequest.put("InvLocation_itemLocation", itemLocation);
			incomingRequest.put("InvBinLocation_icRc", icRcString);
			incomingRequest.put("RequisitionLine_icReqLine", icReqLine);
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