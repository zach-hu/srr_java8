package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.BudgetCenter;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountBudgetCenterSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BudgetCenter budgetCenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			if (budgetCenter == null)
			{
				budgetCenter = new BudgetCenter();
			}
			if (!HiltonUtility.isEmpty(budgetCenter.getBudget1()) && !budgetCenter.getBudget1().equals("*"))
				incomingRequest.put("Account_fld1", budgetCenter.getBudget1());
			if (!HiltonUtility.isEmpty(budgetCenter.getBudget2()) && !budgetCenter.getBudget2().equals("*"))
				incomingRequest.put("Account_fld2", budgetCenter.getBudget2());
			if (!HiltonUtility.isEmpty(budgetCenter.getBudget3()) && !budgetCenter.getBudget3().equals("*"))
				incomingRequest.put("Account_fld3", budgetCenter.getBudget3());
			if (!HiltonUtility.isEmpty(budgetCenter.getBudget4()) && !budgetCenter.getBudget4().equals("*"))
				incomingRequest.put("Account_fld4", budgetCenter.getBudget4());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}