/**
 * 
 */
package com.puridiom.service.budget.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.puridiom.service.budget.BudgetFlag;
import com.puridiom.service.budget.BudgetReturn;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AccountListBudgetServiceFlagUpdate extends Task
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
			int[][] checkBudgetResults = (int[][]) incomingRequest.get("checkBudgetResults");
			List accountList = (List) incomingRequest.get("accountList");

			for (int i = 0; i < checkBudgetResults.length; i++)
			{
				Account account = (Account) accountList.get(i);

				account.setBudgetFlag(this.getBudgetFlagAccount(checkBudgetResults[i]));
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AccountListBudgetServiceFlagUpdate error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private String getBudgetFlagAccount(int[] checkBudgetResults)
	{
		String budgetFlag = BudgetFlag.BUDGETPASSED;

		for (int i = 0; i < checkBudgetResults.length; i++)
		{
			int budgetCheck = checkBudgetResults[i];

			budgetCheck = Math.abs(budgetCheck) * (-1);

			if ((budgetCheck == BudgetReturn.NOTENOUGHBALANCE) || (budgetCheck == BudgetReturn.OVERTOLERANCE))
			{
				budgetFlag = BudgetFlag.NOTENOUGHBALANCE;
				break;

			} else if ((budgetCheck == BudgetReturn.BUDGETNOEXISTS) || (budgetCheck == BudgetReturn.BUDGETNOTACTIVE))
			{
				budgetFlag = BudgetFlag.BUDGETNOEXISTS;

			} else if (HiltonUtility.isEmpty(budgetFlag) && ((budgetCheck == BudgetReturn.NOAUTHORITY) || (budgetCheck == BudgetReturn.AUTHORITYNOTACTIVE)))
			{
				budgetFlag = BudgetFlag.NOAUTHORITY;
			}
		}

		return budgetFlag;
	}
}