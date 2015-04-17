/**
 * Created on Dec 9, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryTransferReqUnitPrice.java
 */
 
package com.tsa.puridiom.inventory.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class TransferReqUnitPrice extends Task
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
			InvItem item = (InvItem)incomingRequest.get("invItem");
			incomingRequest.put("DisbLine_uintOfIssue", item.getUnitOfIssue());
			incomingRequest.put("InvItem_factor", item.getFactor());
			
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
