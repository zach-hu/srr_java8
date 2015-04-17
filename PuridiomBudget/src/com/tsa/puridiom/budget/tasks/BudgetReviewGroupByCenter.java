/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetAudit;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetReviewGroupByCenter extends Task
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
			List budgetReviewList = (List) incomingRequest.get("budgetReviewList");
			List budgetGroupReviewList = new ArrayList();
			BudgetCenter budgetGroupCenter = new BudgetCenter();
			BigDecimal tranAmount = new BigDecimal(0);

			for (int i = 0; i < budgetReviewList.size(); i++)
			{
				Object[] budgetReview = (Object[]) budgetReviewList.get(i);
				BudgetAudit bAudit = (BudgetAudit) budgetReview[0];
				BudgetCenter bCenter = (BudgetCenter) budgetReview[1];
				BigDecimal budgetId = bCenter.getBudgetId();

				if (budgetGroupCenter.getBudgetId().compareTo(budgetId) != 0)
				{
					if (i > 0)
					{
						budgetGroupReviewList.add(new Object[] { tranAmount, budgetGroupCenter });
					}

					budgetGroupCenter = bCenter;
					tranAmount = new BigDecimal(0);
				}

				tranAmount = tranAmount.add(bAudit.getTranAmt());

				if ((i + 1) == budgetReviewList.size())
				{
					budgetGroupReviewList.add(new Object[] { tranAmount, budgetGroupCenter });
				}
			}

			result = budgetGroupReviewList;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetReviewGroupByCenter error " + e.getMessage());

			throw e;
		}

		return result;
	}

}