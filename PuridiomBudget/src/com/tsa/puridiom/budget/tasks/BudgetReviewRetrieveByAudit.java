/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetReviewRetrieveByAudit extends Task
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
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String hicString = (String) incomingRequest.get("budgetHeaderIc");
			String makeString = (String) incomingRequest.get("budgetMake");
			String typeString = (String) incomingRequest.get("budgetAction");
			BigDecimal hic = new BigDecimal(hicString);

			String queryString = "from BudgetAudit as BudgetAudit, BudgetCenter as BudgetCenter where (BudgetAudit.budgetId = BudgetCenter.budgetId) and BudgetAudit.actionMake = ? and BudgetAudit.icHeader = ? order by BudgetAudit.budgetId";

			result = dbs.query(queryString, new Object[] { makeString, hic, }, new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL });

			this.setStatus(dbs.getStatus());

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetReviewRetrieveByAudit error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
