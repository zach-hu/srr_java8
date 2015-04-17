/**
 *
 * Created on Feb 9, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.disbline.tasks.DisbLineExtendedCreateCheck.java
 *
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class DisbLineBackorderExtendedCreateCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			DisbLine disbLine = (DisbLine) incomingRequest.get("disbLine") ;
			String invLocation = reqLine.getItemLocation();
			String itemNumber = reqLine.getItemNumber();
			incomingRequest.put("InvBinLocation_itemNumber", itemNumber);
			incomingRequest.put("InvItem_itemNumber", itemNumber);
			incomingRequest.put("InvLocation_itemNumber", itemNumber);
			incomingRequest.put("InvBinLocation_itemLocation", invLocation);
			incomingRequest.put("InvLocation_itemLocation", invLocation);
			incomingRequest.put("Account_icHeader", reqLine.getIcReqHeader().toString());
			incomingRequest.put("Account_icLine", reqLine.getIcReqLine().toString());
			BigDecimal qtyRequested = reqLine.getBackordered();
			incomingRequest.put("qtyRequested", qtyRequested);
			BigDecimal qtyToDisburse = qtyRequested;
			incomingRequest.put("qtyToDisburse", qtyToDisburse);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("DisbLineExtendedCreateCheck", e);
		}
		return null;
	}

}
