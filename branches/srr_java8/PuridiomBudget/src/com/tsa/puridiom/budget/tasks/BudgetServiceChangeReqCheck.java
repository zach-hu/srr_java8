/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.puridiom.service.budget.BudgetReturn;
import com.puridiom.service.budget.BudgetServiceJob;
import com.puridiom.service.budget.BudgetServiceSetError;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetServiceChangeReqCheck extends Task
{
	public void delayMe(int offset)
	{
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		long diff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();

		while (diff < (offset * 1000l))
		{
			calEnd = Calendar.getInstance();
			diff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
		}

		System.err.println("time dif: " + ((calEnd.getTimeInMillis() - calStart.getTimeInMillis()) / 1000));
	}

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

			String organizationId = (String) incomingRequest.get("organizationId");
			List errorList = (List) incomingRequest.get("errorList");
			Set distinctErrors = (Set) incomingRequest.get("distinctErrors");
			PoHeader ph = (PoHeader) incomingRequest.get("poHeaderOriginal");
			String budgetCurrency = HiltonUtility.ckNull((String) incomingRequest.get("budgetCurrency"));

			BigDecimal total = new BigDecimal(0);
			if(ph == null)
			{
				ph = new PoHeader();
			}
			else
			{
				total = HiltonUtility.getConvertBaseTotal(ph.getTotal(), budgetCurrency, organizationId);;
			}

			String[][] budgetKeys = (String[][]) incomingRequest.get("budgetKeys");
			String[] authorities = (String[]) incomingRequest.get("authorities");
			BigDecimal[] amounts = (BigDecimal[]) incomingRequest.get("amounts");
			int[][] checkBudgetResults;

			// delayMe(2);

			if (errorList == null)
			{
				errorList = new ArrayList();
				distinctErrors = new HashSet();
			}

			// delayMe(6);

			checkBudgetResults = budgetServiceJob.budgetCheckChangeReq(budgetKeys, authorities, amounts, total, organizationId);

			for (int i = 0; i < checkBudgetResults.length; i++)
			{
				for (int j = 0; j < checkBudgetResults[i].length; j++)
				{
					if (checkBudgetResults[i][j] != BudgetReturn.NOEXCEPTION)
					{
						BudgetServiceSetError.budgetError(errorList, distinctErrors, checkBudgetResults[i][j], budgetKeys[i], authorities[i], organizationId);
					}
				}
			}

			incomingRequest.put("distinctErrors", distinctErrors);
			incomingRequest.put("checkBudgetResults", checkBudgetResults);

			result = errorList;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceCheck error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
