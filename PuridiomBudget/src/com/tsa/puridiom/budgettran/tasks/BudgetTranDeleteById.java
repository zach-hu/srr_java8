package com.tsa.puridiom.budgettran.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BudgetTranDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BudgetTran budgetTran = (BudgetTran)incomingRequest.get("budgetTran");
		if(budgetTran == null)
		{
			budgetTran = new BudgetTran();
		}
		BudgetTranSetValuesPK setValues = new BudgetTranSetValuesPK();
		setValues.setValues(incomingRequest, budgetTran);
		dbs.delete(budgetTran);
		this.setStatus(dbs.getStatus()) ;
		return budgetTran ;
	}

}