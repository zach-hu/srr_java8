package com.tsa.puridiom.budgetdrawer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BudgetDrawerListRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String budgetId = (String ) incomingRequest.get("BudgetCenter_budgetId");

			String queryString = "from BudgetDrawer BudgetDrawer where BudgetDrawer.id.budgetId = ? ";
			List resultList = dbs.query(queryString, new Object[] {budgetId } , new Type[] { Hibernate.STRING}) ;
			result = resultList;

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