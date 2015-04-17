/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puridiom.service.budget.BudgetReturn;
import com.puridiom.service.budget.BudgetServiceSetError;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetServiceGetException extends Task
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
			String organizationId = (String) incomingRequest.get("organizationId");
			BudgetReturn budgetReturn = (BudgetReturn) incomingRequest.get("budgetReturn");
			String[][] budgetKeys = (String[][]) incomingRequest.get("budgetKeys");
			String[] authorities = (String[]) incomingRequest.get("authorities");
			List errorList = new ArrayList();
			Set distinctErrors = new HashSet();
			
			for (int i = 0; i < budgetKeys.length; i++)
			{
				int exception = budgetReturn.getBudgetException(budgetKeys[i]);

				if (exception < BudgetReturn.NOEXCEPTION)
				{
					BudgetServiceSetError.budgetError(errorList, distinctErrors, exception, budgetKeys[i], authorities[i], organizationId);
				}
			}
			
			incomingRequest.put("distinctErrors", distinctErrors);
			
			result = errorList;
			
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceGetException error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
