package com.tsa.puridiom.budgetcenter.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BudgetCenterRetrieveByIdList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List resultList = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String budgetId = (String ) incomingRequest.get("BudgetCenter_budgetId");

			String queryString = "from BudgetCenter BudgetCenter where BudgetCenter.id = ? ";
			resultList = dbs.query(queryString, new Object[] {budgetId } , new Type[] { Hibernate.STRING}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}

}