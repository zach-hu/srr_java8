/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.puridiom.service.budget.BudgetAction;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetSetupActions extends Task
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
			String budgetType = (String) incomingRequest.get("budgetServiceType");
			String budgetAction = (String) incomingRequest.get("budgetServiceAction");
			List budgetActions = new ArrayList();

			if (HiltonUtility.isEmpty(budgetAction))
			{
				budgetAction = "FORWARD";
			}

			if (budgetAction.equalsIgnoreCase("FORWARD"))
			{
				budgetActions = BudgetAction.getForwardActions(budgetType);

			} else if (budgetAction.equalsIgnoreCase("CANCEL"))
			{
				budgetActions = BudgetAction.getCancelActions(budgetType);

			} else if (budgetAction.equalsIgnoreCase("CLOSE"))
			{
				budgetActions = BudgetAction.getCloseActions(budgetType);

			} else if (budgetAction.equalsIgnoreCase("DELETE"))
			{
				budgetActions = BudgetAction.getDeleteActions(budgetType, (String) incomingRequest.get("budgetServiceSubAction"));
			}

			result = budgetActions;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupActions error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
