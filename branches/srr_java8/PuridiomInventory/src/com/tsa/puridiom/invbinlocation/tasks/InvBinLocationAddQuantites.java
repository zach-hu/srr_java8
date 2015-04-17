/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationAddQuantites.java
 *
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationAddQuantites
 */
public class InvBinLocationAddQuantites extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;

            String organizationId = (String)incomingRequest.get("organizationId");
			List bins = (List)incomingRequest.get("bins");
			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
			BigDecimal onHand = new BigDecimal(0);
			BigDecimal allocated = new BigDecimal(0);
			BigDecimal duomOnHand = new BigDecimal(0);
			BigDecimal duomAllocated = new BigDecimal(0);
			for(int i = 0; i < bins.size(); i++)
			{
				InvBinLocation bin = (InvBinLocation)bins.get(i);
				if(bin.getQtyOnHand() != null)
				{
					onHand = onHand.add(bin.getQtyOnHand());
				}
				if(bin.getQtyAlloc() != null)
				{
					allocated = allocated.add(bin.getQtyAlloc());
				}
				if(bin.getDuomQtyAlloc() != null)
				{
					duomAllocated = duomAllocated.add(bin.getDuomQtyAlloc());
				}
				if(bin.getDuomQtyOnHand() != null)
				{
					duomOnHand = duomOnHand.add(bin.getDuomQtyOnHand());
				}
			}

			if (PropertiesManager.getInstance(organizationId).getProperty("INVENTORY", "ALLOWNEGATIVEQUANTITIES", "N").equalsIgnoreCase("N")) {
				if (onHand.compareTo(new BigDecimal(0)) < 0) onHand = new BigDecimal(0);
				if (allocated.compareTo(new BigDecimal(0)) < 0) allocated = new BigDecimal(0);
				if (duomOnHand.compareTo(new BigDecimal(0)) < 0) duomOnHand = new BigDecimal(0);
				if (duomAllocated.compareTo(new BigDecimal(0)) < 0) duomAllocated = new BigDecimal(0);
			}

			//onHand = onHand.add(invLocation.getQtyOnHand());
			invLocation.setQtyAlloc(allocated);
			invLocation.setQtyOnHand(onHand);
			invLocation.setDuomQtyAlloc(duomAllocated);
			invLocation.setDuomQtyOnHand(duomOnHand);
			result = invLocation;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}
