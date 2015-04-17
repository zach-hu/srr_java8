package com.tsa.puridiom.budget.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BudgetReviewRetrieveByDate extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String hicString = (String) incomingRequest.get("budgetHeaderIc");
			BigDecimal hic = new BigDecimal ( hicString );
			String queryString = "SELECT BudgetAudit.budgetId, max(BudgetCenter.budget1), max(BudgetCenter.budget2), max(BudgetCenter.budget3), max(BudgetCenter.budget4), max(BudgetCenter.budget5), max(BudgetCenter.budget6), max(BudgetCenter.budgeted), sum(BudgetAudit.tranAmt), max(BudgetCenter.preEncumbered), max(BudgetCenter.encumbered), max(BudgetCenter.expensed), max(BudgetCenter.comments) " +
			"FROM BudgetAudit as BudgetAudit, BudgetCenter as BudgetCenter " +
			"WHERE BudgetCenter.budgetId = BudgetAudit.budgetId and " +
			"BudgetAudit.icHeader = ? and " +
			"BudgetAudit.actionDate = " +
				"(SELECT BudgetAudit.actionDate " +
				"FROM BudgetAudit as BudgetAudit WHERE BudgetAudit.auditId = (" +
					"SELECT max(BudgetAudit.auditId) FROM BudgetAudit as BudgetAudit " +
					"WHERE BudgetAudit.icHeader = ?)) GROUP BY BudgetAudit.budgetId";
			result = dbs.query(queryString, new Object[] {hic, hic} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});
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