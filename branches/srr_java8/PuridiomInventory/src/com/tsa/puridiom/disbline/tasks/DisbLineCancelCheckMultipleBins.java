/*
 * Created on Dec 11, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineCancelCheckMultipleBins.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbLineCancelCheckMultipleBins extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			incomingRequest.put("cancelall", "?");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
