/**
 * Created on May 26, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.ItemDetailUsageSetup.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * ItemDetailUsageSetup 
 */
public class ItemDetailUsageSetup extends Task
{
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String itemNumber = (String)incomingRequest.get("InvItem_itemNumber");
			incomingRequest.put("InvLocation_itemNumber", itemNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		
		return super.executeTask(object);
	}

}
