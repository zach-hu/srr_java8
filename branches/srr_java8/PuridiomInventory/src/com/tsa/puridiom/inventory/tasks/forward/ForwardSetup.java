/*
 * Created on Dec 4, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.forwardForwarSetup.java
 */
 
package com.tsa.puridiom.inventory.tasks.forward;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ForwardSetup extends Task
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
			String icDisbHeader = (String)incomingRequest.get("DisbHeader_icDsbHeader");
			incomingRequest.put("DisbLine_icDsbHeader", icDisbHeader);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
