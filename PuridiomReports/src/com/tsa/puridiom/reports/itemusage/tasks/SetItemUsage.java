/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.SetItemUsage.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetItemUsage extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			InvItem item = (InvItem)incomingRequest.get("item");
			ItemDetailUsage usage = (ItemDetailUsage)incomingRequest.get("itemDetailUsage");
			usage.setDescription(item.getDescription());
			usage.setLastPoNum((String)incomingRequest.get("PoHeader_poNumber"));
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader != null)
			{
				usage.setLastVend(poHeader.getVendorName());
			}
			
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
