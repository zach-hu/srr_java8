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

public class BudgetCheckPreEmcumberedEntities extends Task
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

			StringBuffer query = new StringBuffer("from BudgetTran as bt where bt.auditId in (select ba.auditId ");
			query.append("from BudgetCenter as bc, BudgetAudit as ba where bc.budgetId = ba.budgetId ");
			query.append("and ba.parentType = 'IVC') and bt.preEncumbered <> '0' and bt.encumbered <> '0' ");
			query.append("and bt.expensed = '0' ");
			List resultList = dbs.query(query.toString()) ;

			if (resultList != null && resultList.size() > 0)
			{
				for(int x = 0; x < resultList.size(); x++)
				{
					BudgetTran budgetTran = (BudgetTran)resultList.get(x);
					BigDecimal preEncumbered = HiltonUtility.ckNull(budgetTran.getPreEncumbered());
					BigDecimal encumbered = HiltonUtility.ckNull(budgetTran.getEncumbered());
					encumbered = encumbered.add(preEncumbered);
	            	BigDecimal budgetId = budgetTran.getBudgetId();

	            	String queryString = "from BudgetCenter as bc where bc.budgetId = ? ";
					List resultListString = dbs.query(queryString, new Object[] {budgetId, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

					UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
					String userId = (String) incomingRequest.get("userId");
					BigDecimal tranIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
					if (resultListString != null && resultListString.size() > 0)
					{
						BudgetCenter budgetCenter = (BudgetCenter)resultListString.get(0);
						BigDecimal bcPreEncumbered = HiltonUtility.ckNull(budgetCenter.getPreEncumbered());
						BigDecimal bcEncumbered = HiltonUtility.ckNull(budgetCenter.getEncumbered());
						BigDecimal bcExpensed = HiltonUtility.ckNull(budgetCenter.getExpensed());
						bcPreEncumbered =  bcPreEncumbered.subtract(preEncumbered);
						bcEncumbered = bcEncumbered.subtract(encumbered);

						budgetCenter.setEncumbered(bcEncumbered);
						budgetCenter.setPreEncumbered(bcPreEncumbered);
						budgetCenter.setExpensed(bcExpensed);
						budgetCenter.setLastChangeBy(userId) ;
						budgetCenter.setLastChangeDate(new java.util.Date()) ;
						budgetCenter.setLastTranId(tranIc) ;

						dbs.getSession().merge(budgetCenter) ;
						this.setStatus(dbs.getStatus()) ;
					}

					preEncumbered = new BigDecimal(0);
					budgetTran.setPreEncumbered(preEncumbered);
					budgetTran.setEncumbered(encumbered);

	            	dbs.update(budgetTran);
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