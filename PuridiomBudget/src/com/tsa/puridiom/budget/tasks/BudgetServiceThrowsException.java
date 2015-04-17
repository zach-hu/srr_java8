/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class BudgetServiceThrowsException extends Task
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
			String failurePage = "";

			BudgetServiceUnlock budgetServiceUnlock = new BudgetServiceUnlock();

			budgetServiceUnlock.executeTask(incomingRequest);

			if (documentType.equalsIgnoreCase("REQ"))
			{
				failurePage = "/requests/req_budget_service_check_no_popup.jsp";

			} else if (documentType.equalsIgnoreCase("PO"))
			{
				failurePage = "/orders/po_budget_service_check_no_popup.jsp";

			} else if (documentType.equalsIgnoreCase("IVC"))
			{
				failurePage = "/invoice/iv_budget_service_check_no_popup.jsp";
			}

			if (!HiltonUtility.isEmpty(failurePage))
			{
				incomingRequest.put("failurePage", failurePage);

				throw new TsaException("Budget Update with errors");
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceThrowsException error " + e.getMessage());

			throw e;
		}

		return result;
	}

}