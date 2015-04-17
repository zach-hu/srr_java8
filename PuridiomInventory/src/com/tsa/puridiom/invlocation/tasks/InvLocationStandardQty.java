/*
 * Created on Nov 20, 2003 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo 
 */
public class InvLocationStandardQty extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
		try
		{
			BigDecimal qtyOnHand = (BigDecimal)incomingRequest.get("InvLocation_qtyOnHand");
			qtyOnHand = inventory.getQtyOnHand().subtract(qtyOnHand);
			inventory.setQtyOnHand(qtyOnHand);
			
			BigDecimal qtyAlloc = (BigDecimal)incomingRequest.get("InvLocation_qtyAlloc");
			qtyAlloc = qtyAlloc.add(inventory.getQtyAlloc());
			inventory.setQtyAlloc(qtyAlloc);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return inventory;
	}

}
