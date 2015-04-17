package com.tsa.puridiom.budget.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class BudgetSetMessageErrorUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String budgetExceptionMessage = HiltonUtility.ckNull((String)incomingRequest.get("budgetExceptionMessage"));
			String successPage="/budget/budget_exception_message.jsp";
			incomingRequest.put("successPage", successPage);
			String errorMessage = "";


			if (budgetExceptionMessage.equalsIgnoreCase("BudgetLockExpired"))
			{

				incomingRequest.put("errorMessage", "BudgetLockExpired");
			}
			else if (budgetExceptionMessage.equalsIgnoreCase("BudgetLockTokenBad"))			{

				incomingRequest.put("errorMessage", "BudgetLockTokenBad");
			}


			incomingRequest.put("successPage", successPage);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.debug(this, "Error SetMessageErrorPoDuplicate: \r\n" + e.getMessage());
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}