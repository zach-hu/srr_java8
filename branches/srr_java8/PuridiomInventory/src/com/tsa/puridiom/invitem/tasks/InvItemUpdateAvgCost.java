/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationAddQuantites.java
 *
 */
package com.tsa.puridiom.invitem.tasks;

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
public class InvItemUpdateAvgCost extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		this.setStatus(Status.SUCCEEDED) ;
		try
		{
			Map incomingRequest = (Map)object;

			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
			List locations = (List)incomingRequest.get("invLocationList");
			InvItem invItem = (InvItem) incomingRequest.get("invItem") ;

			BigDecimal onHand = new BigDecimal(0);
			BigDecimal totalCost = new BigDecimal(0) ;
			for(int i = 0; i < locations.size(); i++)
			{
				InvLocation location = (InvLocation)locations.get(i);
				if(location.getQtyOnHand() != null)
				{
					onHand = onHand.add(location.getQtyOnHand());
					totalCost = totalCost.add(location.getQtyOnHand().multiply(location.getAverageCost())) ;
				}
			}
			if (onHand.compareTo(new BigDecimal(0)) == 0) {
			} else {
				invItem.setAverageCost(totalCost.divide(onHand, 2, BigDecimal.ROUND_UP)) ;
			}
			result = invItem;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}
