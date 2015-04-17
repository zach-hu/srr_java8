/*
 * Created on Dec 12, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineExtendedCancelUpdateInventory.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbLineExtendedCancelUpdateInventory extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			BigDecimal qty = disbLine.getQuantity();
			BigDecimal alloc = bin.getQtyAlloc();
			BigDecimal newAlloc = alloc.subtract(qty);
			bin.setQtyAlloc(newAlloc);
			InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
			alloc = inventory.getQtyAlloc();
			newAlloc = alloc.subtract(qty);
			incomingRequest.put("invBinLocation", bin);
			incomingRequest.put("invLocation", inventory);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
