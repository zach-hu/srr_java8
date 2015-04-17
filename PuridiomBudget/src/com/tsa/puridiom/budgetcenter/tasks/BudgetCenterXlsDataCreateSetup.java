/**
 * 
 */
package com.tsa.puridiom.budgetcenter.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetCenterXlsDataCreateSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_budgeted")))
			{
				incomingRequest.put("BudgetCenter_budgeted", "0");
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_preEncumbered")))
			{
				incomingRequest.put("BudgetCenter_preEncumbered", "0");
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_encumbered")))
			{
				incomingRequest.put("BudgetCenter_encumbered", "0");
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_expensed")))
			{
				incomingRequest.put("BudgetCenter_expensed", "0");
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_balance")))
			{
				incomingRequest.put("BudgetCenter_balance", this.getBalance(incomingRequest));
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetCenter_status")))
			{
				incomingRequest.put("BudgetCenter_status", GeneralStatus.STATUS_PERMANENT);
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetCenterXlsDataCreateSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private String getBalance(Map incomingRequest)
	{
		BigDecimal balance;
		BigDecimal budgeted = new BigDecimal((String) incomingRequest.get("BudgetCenter_budgeted")).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal preEncumbered = new BigDecimal((String) incomingRequest.get("BudgetCenter_preEncumbered")).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal encumbered = new BigDecimal((String) incomingRequest.get("BudgetCenter_encumbered")).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal expensed = new BigDecimal((String) incomingRequest.get("BudgetCenter_expensed")).setScale(2, BigDecimal.ROUND_HALF_UP);

		balance = budgeted.subtract(preEncumbered.add(encumbered).add(expensed)).setScale(2, BigDecimal.ROUND_HALF_UP);

		Log.debug(this, "XlsData got, budgeted = " + budgeted + ", preEncumbered = " + preEncumbered + ", encumbered = " + encumbered + ", expensed = " + expensed + ", balance = " + balance);

		return balance.toString();
	}

}