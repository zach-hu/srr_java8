/**
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoLineCloseBudgetServiceSetup extends Task
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
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			List poLineList = new ArrayList();

			poLineList.add(poLine);

			incomingRequest.put("header", poh);
			incomingRequest.put("lineItems", poLineList);
			incomingRequest.put("formtype", "PO");
			incomingRequest.put("budgetServiceAction", "CLOSE");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoLineCancelBudgetServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}
}