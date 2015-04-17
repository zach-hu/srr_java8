package com.tsa.puridiom.budgetcenter.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class BudgetCenterIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String BudgetCenter_budgetId = (String)incomingRequest.get("BudgetCenter_budgetId");
		if(Utility.isEmpty(BudgetCenter_budgetId))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Budget Id is necessary to retrieve a budget");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}
		return null;
	}
}