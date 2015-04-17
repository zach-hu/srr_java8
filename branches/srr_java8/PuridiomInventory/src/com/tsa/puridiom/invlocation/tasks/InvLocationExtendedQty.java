/**
 * 
 * Created on Feb 10, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationExtendedQty.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationExtendedQty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
		try
		{
			List bins = (List)incomingRequest.get("invBinLocations");
			
			BigDecimal totalQtyOnHand = new BigDecimal(0);
			BigDecimal totalQtyAlloc = new BigDecimal(0);
			for(int i = 0; i < bins.size(); i++)
			{
				InvBinLocation bin = (InvBinLocation)bins.get(i);
				BigDecimal qtyOnHand = bin.getQtyOnHand();
				totalQtyOnHand = totalQtyOnHand.add(qtyOnHand);
				BigDecimal qtyAlloc = bin.getQtyAlloc();
				totalQtyAlloc = totalQtyAlloc.add(qtyAlloc);
			}
			inventory.setQtyAlloc(totalQtyAlloc);
			inventory.setQtyOnHand(totalQtyOnHand);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			throw new TsaException(this.getName(), e);
		}
		finally
		{
			Log.debug(this.getName(), "inventory");
		}
		return inventory;
	}
}
