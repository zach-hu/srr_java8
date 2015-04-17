/**
 * Created on May 19, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.reports.itemusage.taks.ItemUsageStart.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ItemDetailUsageStart extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			ItemDetailUsage usage = new ItemDetailUsage();
			String item = (String)incomingRequest.get("InvItem_itemNumber");
			usage.setItemNumber(item);
			ret = usage;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		
		return ret;
	}

}
