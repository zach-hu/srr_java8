package com.tsa.puridiom.budget.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BudgetReviewRetrieve extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String hicString = (String) incomingRequest.get("budgetHeaderIc");
			String makeString = (String) incomingRequest.get("budgetMake") ;
			String typeString = (String) incomingRequest.get("budgetAction") ;
			BigDecimal hic = new BigDecimal ( hicString );

			String queryString = "from BudgetTran as BudgetTran, BudgetAudit as BudgetAudit, BudgetCenter as BudgetCenter where (BudgetAudit.auditId = BudgetTran.auditId) and (BudgetAudit.budgetId = BudgetCenter.budgetId) and BudgetAudit.actionMake = ? and BudgetAudit.icHeader = ? ";
			result = dbs.query(queryString, new Object[] {makeString, hic, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}