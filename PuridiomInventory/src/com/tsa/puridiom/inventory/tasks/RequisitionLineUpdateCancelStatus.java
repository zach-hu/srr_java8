/*
 * Created on Dec 10, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryRequisitionLineUpdateCancelStatus.java
 */
 
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class RequisitionLineUpdateCancelStatus extends Task
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
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			reqLine.setBackordered(new BigDecimal(0));
			reqLine.setStatus(DocumentStatus.CANCELLED);
			ret = reqLine;
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
