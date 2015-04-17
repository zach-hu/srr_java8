/*
 * Created on Nov 11, 2003 
 */
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SetQuantities extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvLocation location = (InvLocation)incomingRequest.get("inventoryLocation");
		BigDecimal qtyOnHand = location.getQtyOnHand();
		BigDecimal qtyAlloc = location.getQtyAlloc();
		BigDecimal bdBackorderd = (BigDecimal)incomingRequest.get("backorderd");
		//need to use qty available for the disbursement not the qty on hand
		BigDecimal bdLQtyOnHand = qtyOnHand.subtract(qtyAlloc);
		BigDecimal bdQty = new BigDecimal(0);
		
		
		
		return incomingRequest;
	}

}
