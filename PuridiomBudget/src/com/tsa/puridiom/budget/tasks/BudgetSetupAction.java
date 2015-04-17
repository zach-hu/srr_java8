/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetSetupAction extends Task
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
			String documentType = (String) incomingRequest.get("formtype");
			String budgetAction = "";

			if (documentType.equalsIgnoreCase("REQ"))
			{
				budgetAction = (String) incomingRequest.get("reqaction");
			} else if (documentType.equalsIgnoreCase("PO"))
			{
				budgetAction = (String) incomingRequest.get("poaction");
			} else if (documentType.equalsIgnoreCase("IVC"))
			{
				budgetAction = (String) incomingRequest.get("invoiceaction");
			}

			result = HiltonUtility.ckNull(budgetAction);
			
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupAction error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
