/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetServiceSetCheckStatus extends Task
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
			List errorList = (List) incomingRequest.get("errorList");
			String checkStatus = "PASSED";

			for (Iterator iterator = errorList.iterator(); iterator.hasNext();)
			{
				Hashtable ht = (Hashtable) iterator.next();
				int exception = Integer.parseInt(((String) ht.get("budgetException")));

				if (exception < 0)
				{
					checkStatus = "FAILED";
					break;
				} else
				{
					checkStatus = "WARNING";
				}
			}

			result = checkStatus;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceSetCheckStatus error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
