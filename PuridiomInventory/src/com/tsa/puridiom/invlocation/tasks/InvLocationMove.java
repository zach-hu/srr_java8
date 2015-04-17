/**
 * 
 * Created on Feb 11, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvLocationMove.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationMove extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			InvLocation fromLocation = (InvLocation) incomingRequest.get("fromLocation");
			InvLocation toLocation = (InvLocation) incomingRequest.get("toLocation");
			String qtyToMove = (String)incomingRequest.get("qtyToMove");
			BigDecimal qty = new BigDecimal(qtyToMove);
			BigDecimal tempQty = fromLocation.getQtyOnHand();
			tempQty = tempQty.subtract(qty);
			fromLocation.setQtyOnHand(tempQty);
			tempQty = toLocation.getQtyOnHand();
			tempQty = tempQty.add(qty);
			toLocation.setQtyOnHand(tempQty);
			incomingRequest.put("fromLocation", fromLocation);
			incomingRequest.put("toLocation", toLocation);
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
