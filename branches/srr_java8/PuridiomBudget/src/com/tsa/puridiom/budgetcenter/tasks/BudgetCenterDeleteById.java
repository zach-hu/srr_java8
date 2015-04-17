package com.tsa.puridiom.budgetcenter.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BudgetCenterDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BudgetCenter budgetCenter = (BudgetCenter)incomingRequest.get("budgetCenter");
		if(budgetCenter == null)
		{
			budgetCenter = new BudgetCenter();
		}
		BudgetCenterSetValuesPK setValues = new BudgetCenterSetValuesPK();
		setValues.setValues(incomingRequest, budgetCenter);
		dbs.delete(budgetCenter);
		this.setStatus(dbs.getStatus()) ;
		return budgetCenter ;
	}

}