/**
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoCancelBudgetServiceSetup extends Task
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
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader");

			incomingRequest.put("header", poh);
			incomingRequest.put("lineItems", incomingRequest.get("poLineList"));
			incomingRequest.put("formtype", "PO");
			incomingRequest.put("budgetServiceAction", "CANCEL");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoCancelBudgetServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}
}