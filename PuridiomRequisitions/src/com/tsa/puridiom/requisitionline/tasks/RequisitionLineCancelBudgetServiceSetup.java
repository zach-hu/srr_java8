/**
 * 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionLineCancelBudgetServiceSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			List requisitionLineList = new ArrayList();

			requisitionLineList.add(requisitionLine);

			incomingRequest.put("header", rqh);
			incomingRequest.put("lineItems", requisitionLineList);
			incomingRequest.put("formtype", "REQ");
			incomingRequest.put("budgetServiceAction", "CANCEL");

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionLineCancelBudgetServiceSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}