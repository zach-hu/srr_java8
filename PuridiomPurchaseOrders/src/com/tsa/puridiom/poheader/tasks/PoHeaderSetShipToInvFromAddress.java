/**
 *
 * Created on Jan 18, 2008
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetShipToInvFromAddress.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoHeaderSetShipToInvFromAddress extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			Address shipToAddress = (Address)incomingRequest.get("shipToAddress");
            String  shipToInv = "N";

			if(shipToAddress != null)
			{
                shipToInv = shipToAddress.getInventory();
			}
            if (Utility.isEmpty(shipToInv))
            {
                shipToInv = "N";
            }
            poHeader.setShipToInv(shipToInv);
			ret = poHeader;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tax Code could not be retrieved.", e);
		}

		return ret;
	}

}
