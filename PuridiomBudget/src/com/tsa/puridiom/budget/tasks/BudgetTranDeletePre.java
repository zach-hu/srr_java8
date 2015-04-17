/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.BudgetTran;
import com.tsa.puridiom.budgettran.tasks.BudgetTranSetValuesPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class BudgetTranDeletePre extends Task
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
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String oid = (String)incomingRequest.get("organizationId");

			StringBuffer deleteQuery = new StringBuffer("from BudgetTran as bt where bt.auditId in (select ba.auditId ");
			deleteQuery.append("from BudgetCenter as bc, BudgetAudit as ba where bc.budgetId = ba.budgetId ");
			deleteQuery.append("and ba.parentType = 'IVC') and bt.preEncumbered <> '0' and bt.encumbered <> '0' ");
			deleteQuery.append("and bt.expensed = '0' ");
			
			List deleteResultList = dbs.query(deleteQuery.toString()) ;
			if (deleteResultList != null && deleteResultList.size() > 0)
			{
				for(int x = 0; x < deleteResultList.size(); x++)
				{
					BudgetTran budgetTran = (BudgetTran)deleteResultList.get(x);
					if(budgetTran == null)
					{
						budgetTran = new BudgetTran();
					}
					incomingRequest.put("BudgetTran_tranId",budgetTran.getTranId());
					dbs.delete(budgetTran);
					this.setStatus(dbs.getStatus()) ;
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}