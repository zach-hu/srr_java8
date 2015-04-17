/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.GetPoNumber.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * GetPoNumber 
 */
public class GetPoNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object poNumber = null;
		try
		{
			Map incomingRequest = (Map)object;
			InvItem item = (InvItem)incomingRequest.get("item");
			poNumber = item.getPoNumber();
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return poNumber;
	}

}