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
import com.tsa.puridiom.common.utility.HiltonUtility;


public class RequisitionLineUpdateCancelStatusFromDisb extends Task
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
			String status = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionLine_status"));

			if(status.equalsIgnoreCase(DocumentStatus.CANCELLED)||status.equalsIgnoreCase(DocumentStatus.CLOSED))
			{
				reqLine.setBackordered(new BigDecimal(0));
				reqLine.setStatus(DocumentStatus.CANCELLED);
			}
			else if(status.equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
			{
				//reqLine.setBackordered(new BigDecimal(0));
				reqLine.setStatus(DocumentStatus.REQ_APPROVED);
			}

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
