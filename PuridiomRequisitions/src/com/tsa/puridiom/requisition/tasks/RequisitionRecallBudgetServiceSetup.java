/**
 * 
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionRecallBudgetServiceSetup extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			incomingRequest.put("header", rqh);
			incomingRequest.put("lineItems", incomingRequest.get("requisitionLineList"));
			incomingRequest.put("formtype", "REQ");
			incomingRequest.put("budgetServiceAction", "CANCEL");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionRecallBudgetServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}
}