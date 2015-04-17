/*
 * Created on Dec 10, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryRequisitionHeaderUpdateCancelStatus.java
 */
 
package com.tsa.puridiom.inventory.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class RequisitionHeaderUpdateCancelStatus extends Task
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
			boolean bCancel = true;
			Map incomingRequest = (Map) object;
			List disbLines = (List)incomingRequest.get("disbLines");
			for (Iterator iter = disbLines.iterator(); iter.hasNext();)
			{
				DisbLine disbLine = (DisbLine) iter.next();
				if(!disbLine.getStatus().equals(DocumentStatus.CANCELLED))
				{
					bCancel = false;
				}
			}
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			if(bCancel)
			{
				reqHeader.setStatus(DocumentStatus.CANCELLED);
			}
			ret = reqHeader;
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
