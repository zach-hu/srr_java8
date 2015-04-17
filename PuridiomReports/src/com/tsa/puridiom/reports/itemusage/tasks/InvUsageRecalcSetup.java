/**
 * Created on May 27, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.InvUsageRecalcSetup.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvUsageRecalcSetup 
 */
public class InvUsageRecalcSetup extends Task
{
	
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			InvLocation summary = (InvLocation)incomingRequest.get("summary");
			incomingRequest.put("InvItem_invLocation", summary.getComp_id().getItemLocation());
			
			
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
