package com.tsa.puridiom.budgettran.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BudgetTranSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BudgetTran_tranId", "0");
			incomingRequest.put("BudgetTran_auditId", "0");
			incomingRequest.put("BudgetTran_budgetId", "0");
			incomingRequest.put("BudgetTran_tranType", "");
			incomingRequest.put("BudgetTran_tranDate", Dates.today("", ""));
			incomingRequest.put("BudgetTran_tranTime", "");
			incomingRequest.put("BudgetTran_preEncumbered", "0");
			incomingRequest.put("BudgetTran_encumbered", "0");
			incomingRequest.put("BudgetTran_expensed", "0");
			incomingRequest.put("BudgetTran_balance", "0");
			incomingRequest.put("BudgetTran_owner", "");

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