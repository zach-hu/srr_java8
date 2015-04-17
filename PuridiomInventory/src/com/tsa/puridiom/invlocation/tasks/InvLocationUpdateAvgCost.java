/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationAddQuantites.java
 *
 */
package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationAddQuantites
 */
public class InvLocationUpdateAvgCost extends Task
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

			List bins = (List)incomingRequest.get("bins");
			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");

			BigDecimal onHand = new BigDecimal(0);
			BigDecimal totalCost = new BigDecimal(0) ;
			for(int i = 0; i < bins.size(); i++)
			{
				InvBinLocation bin = (InvBinLocation)bins.get(i);
				if(bin.getQtyOnHand() != null)
				{
					onHand = onHand.add(bin.getQtyOnHand());
					totalCost = totalCost.add(bin.getQtyOnHand().multiply(bin.getCost())) ;
				}
			}

			if (onHand.compareTo(new BigDecimal(0)) == 0) {
			} else {
				invLocation.setAverageCost(totalCost.divide(onHand, 2, BigDecimal.ROUND_UP)) ;
			}

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
