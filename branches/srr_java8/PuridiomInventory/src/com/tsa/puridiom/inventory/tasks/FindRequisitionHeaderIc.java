/**
 * 
 * Created on Feb 9, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.inventory.tasks.FindRequisitionHeaderIc.java
 * 
 */
package com.tsa.puridiom.inventory.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class FindRequisitionHeaderIc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			ret = disbHeader.getIcReqHeader().toString();
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
