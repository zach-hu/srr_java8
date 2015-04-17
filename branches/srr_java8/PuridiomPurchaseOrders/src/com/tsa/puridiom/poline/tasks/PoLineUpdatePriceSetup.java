/**
 * Created on Mar 11, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdatePriceSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineUpdatePriceSetup extends Task
{
	
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			String itemNumber = poLine.getItemNumber();
			BigDecimal unitPrice = poLine.getUnitPrice();
			String itemSource = poLine.getItemSource();
			incomingRequest.put("itemNumber", itemNumber);
			incomingRequest.put("unitPrice", unitPrice);
			incomingRequest.put("itemSource", itemSource.substring(0,2));
			String icReqLine  = Utility.tsaToString(poLine.getIcReqLine());
			incomingRequest.put("icReqLine", icReqLine);
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
