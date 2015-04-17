/**
 * Created on Apr 8, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.HiltonWeb.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoUpdateOnOrderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingrequest = (Map)object;
		try
		{
			PoLine poLine = (PoLine)incomingrequest.get("poLine");
			incomingrequest.put("InvItem_itemNumber", poLine.getItemNumber());
			incomingrequest.put("InvLocation_itemNumber", poLine.getItemNumber());
			incomingrequest.put("InvLocation_itemLocation", incomingrequest.get("shipto"));
			
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
