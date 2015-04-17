package com.tsa.puridiom.budgetdrawer.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BudgetDrawerDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BudgetDrawer budgetDrawer = (BudgetDrawer)incomingRequest.get("budgetDrawer");
		if(budgetDrawer == null)
		{
			budgetDrawer = new BudgetDrawer();
		}
		BudgetDrawerSetValuesPK setValues = new BudgetDrawerSetValuesPK();
		setValues.setValues(incomingRequest, budgetDrawer);
		dbs.delete(budgetDrawer);
		this.setStatus(dbs.getStatus()) ;
		return budgetDrawer ;
	}

}