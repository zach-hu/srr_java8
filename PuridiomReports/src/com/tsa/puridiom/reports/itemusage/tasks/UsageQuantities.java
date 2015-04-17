/**
 * Created on May 19, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.reports.itemusage.taks.UsageQuantities.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * UsageQuantities 
 */
public class UsageQuantities extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest =(Map)object;
			List summary = (List)incomingRequest.get("inventorySummary");
			BigDecimal qtyOnHd = new BigDecimal(0);
			BigDecimal qtyAlloc = new BigDecimal(0);
			BigDecimal qtyAvail = new BigDecimal(0);
			
			for (Iterator iter= summary.iterator(); iter.hasNext();)
			{
				InvLocation loc = (InvLocation) iter.next();
				qtyOnHd = qtyOnHd.add(loc.getQtyOnHand());
				qtyAlloc = qtyAlloc.add(loc.getQtyAlloc());
			}
			qtyAvail = qtyOnHd.subtract(qtyAlloc);
			ItemUsage itemUsage = (ItemUsage)incomingRequest.get("itemUsage");
			itemUsage.setQoh(qtyOnHd);
			itemUsage.setAlloc(qtyAlloc);
			itemUsage.setAvailable(qtyAvail);
			ItemDetailUsage detailUsage = (ItemDetailUsage)incomingRequest.get("itemDetailUsage");
			detailUsage.setQtyOnHand(qtyOnHd);
			detailUsage.setQtyAlloc(qtyAlloc);
			detailUsage.setQtyAvail(qtyAvail);
			
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
