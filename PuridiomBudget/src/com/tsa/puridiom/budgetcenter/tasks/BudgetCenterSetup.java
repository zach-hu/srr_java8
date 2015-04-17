package com.tsa.puridiom.budgetcenter.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BudgetCenterSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BudgetCenter_budgetId", "");
			incomingRequest.put("BudgetCenter_budgetPeriod", "");
			incomingRequest.put("BudgetCenter_budget1", "");
			incomingRequest.put("BudgetCenter_budget2", "");
			incomingRequest.put("BudgetCenter_budget3", "");
			incomingRequest.put("BudgetCenter_budget4", "");
			incomingRequest.put("BudgetCenter_budget5", "");
			incomingRequest.put("BudgetCenter_budget6", "");
			incomingRequest.put("BudgetCenter_budget7", "");
			incomingRequest.put("BudgetCenter_budget8", "");
			incomingRequest.put("BudgetCenter_budget9", "");
			incomingRequest.put("BudgetCenter_budget10", "");
			incomingRequest.put("BudgetCenter_budget11", "");
			incomingRequest.put("BudgetCenter_budget12", "");
			incomingRequest.put("BudgetCenter_budget13", "");
			incomingRequest.put("BudgetCenter_budget14", "");
			incomingRequest.put("BudgetCenter_budget15", "");
			incomingRequest.put("BudgetCenter_budgeted", "0");
			incomingRequest.put("BudgetCenter_preEncumbered", "0");
			incomingRequest.put("BudgetCenter_encumbered", "0");
			incomingRequest.put("BudgetCenter_expensed", "0");
			incomingRequest.put("BudgetCenter_owner", "");
			incomingRequest.put("BudgetCenter_ownerPassword", "");
			incomingRequest.put("BudgetCenter_status", "02");
			incomingRequest.put("BudgetCenter_approved", "");
			incomingRequest.put("BudgetCenter_projectId", "");
			incomingRequest.put("BudgetCenter_comments", "");
			incomingRequest.put("BudgetCenter_lastChangeBy", "");
			incomingRequest.put("BudgetCenter_lastChangeDate", Dates.today("", ""));
			incomingRequest.put("BudgetCenter_lastTranId", "0");
			incomingRequest.put("BudgetCenter_lastAuditId", "0");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}