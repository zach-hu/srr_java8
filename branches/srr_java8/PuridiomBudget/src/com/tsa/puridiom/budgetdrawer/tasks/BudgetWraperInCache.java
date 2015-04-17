/**
 *
 */
package com.tsa.puridiom.budgetdrawer.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.puridiom.service.budget.BudgetServiceJob;
import com.puridiom.service.budget.BudgetUtils;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Johnny
 */
public class BudgetWraperInCache extends Task
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

			BudgetCenter budgetcenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			String budgetServiceYear = budgetcenter.getBudgetPeriod();
			String organizationId = (String) incomingRequest.get("organizationId");
			String[] budgetKey;

			if (budgetcenter!=null)
			{
				budgetKey = BudgetUtils.buildBudgetKeyFromUDFs(budgetcenter, budgetServiceYear, organizationId);

				BudgetServiceJob.getInstance().resetBudgetInCache(budgetKey, organizationId);
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "BudgetServiceUpdate error " + e.getMessage());
			Log.error(this, "BudgetServiceUpdate error " + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return result;
	}
}