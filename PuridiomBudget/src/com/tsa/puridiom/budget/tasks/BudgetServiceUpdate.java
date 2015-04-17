/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.puridiom.service.budget.BudgetReturn;
import com.puridiom.service.budget.BudgetServiceJob;
import com.puridiom.service.budget.exceptions.BudgetLockExpired;
import com.puridiom.service.budget.exceptions.BudgetLockTokenBad;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetServiceUpdate extends Task
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
			BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

			BudgetReturn budgetReturn = (BudgetReturn) incomingRequest.get("budgetReturn");
			String[][] budgetKeys = (String[][]) incomingRequest.get("budgetKeys");
			BigDecimal icHeader = (BigDecimal) incomingRequest.get("budgetIcHeader");
			BigDecimal[] icLines = (BigDecimal[]) incomingRequest.get("budgetIcLines");
			BigDecimal[] amounts = (BigDecimal[]) incomingRequest.get("amounts");
			String[] actions = (String[]) incomingRequest.get("actions");
			String organizationId = (String) incomingRequest.get("organizationId");
			String documentType = (String) incomingRequest.get("formtype");
			boolean isBudgetUpdated = false;
			Map updateArgs = new HashMap();

			updateArgs.put("headerIc", icHeader);
			updateArgs.put("lineIc", icLines);
			updateArgs.put("formType", documentType);

			isBudgetUpdated = budgetServiceJob.updateBudget(budgetKeys, budgetReturn.getLockingToken(), actions, amounts, updateArgs, organizationId);

			result = String.valueOf(isBudgetUpdated);

			this.setStatus(Status.SUCCEEDED);

		} catch (BudgetLockExpired e)
		{
			this.setStatus(Status.FAILED);

			e.printStackTrace();

			Log.error(this, "BudgetLockExpired error " + e.getMessage());

			throw e;
		} catch (BudgetLockTokenBad e)
		{
			this.setStatus(Status.FAILED);

			e.printStackTrace();

			Log.error(this, "BudgetLockTokenBad error " + e.getMessage());

			throw e;
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceUpdate error " + e.getMessage());

			throw e;
		}

		return result;
	}
}