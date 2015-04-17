/**
 * 
 */
package com.puridiom.service.budget.tasks;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.puridiom.service.budget.BudgetFlag;
import com.puridiom.service.budget.BudgetReturn;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionHeaderBudgetServiceFlagUpdate extends Task
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
			String budgetCheckStatus = (String) incomingRequest.get("budgetCheckStatus");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("header");
			List errorList = (List) incomingRequest.get("errorList");
			String budgetServiceFlag = BudgetFlag.BUDGETPASSED;

			if (budgetCheckStatus.equalsIgnoreCase("FAILED"))
			{
				budgetServiceFlag = BudgetFlag.BUDGETFAILED;

			} else if (budgetCheckStatus.equalsIgnoreCase("WARNING"))
			{
				for (Iterator iterator = errorList.iterator(); iterator.hasNext();)
				{
					Hashtable ht = (Hashtable) iterator.next();
					int exception = Integer.parseInt(((String) ht.get("budgetException")));

					exception = Math.abs(exception) * (-1);

					if ((exception == BudgetReturn.NOTENOUGHBALANCE) || (exception == BudgetReturn.OVERTOLERANCE))
					{
						budgetServiceFlag = BudgetFlag.NOTENOUGHBALANCE;
						break;
						
					} else if ((exception == BudgetReturn.BUDGETNOEXISTS) || (exception == BudgetReturn.BUDGETNOTACTIVE))
					{
						budgetServiceFlag = BudgetFlag.BUDGETNOEXISTS;

					} else if (HiltonUtility.isEmpty(budgetServiceFlag) && ((exception == BudgetReturn.NOAUTHORITY) || (exception == BudgetReturn.AUTHORITYNOTACTIVE)))
					{
						budgetServiceFlag = BudgetFlag.NOAUTHORITY;
					}

				}
			}

			requisitionHeader.setBudgetFlag(budgetServiceFlag);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionHeaderBudgetServiceFlagUpdate error " + e.getMessage());

			throw e;
		}

		return result;
	}
}