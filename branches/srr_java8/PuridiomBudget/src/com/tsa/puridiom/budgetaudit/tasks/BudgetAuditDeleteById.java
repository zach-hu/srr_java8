package com.tsa.puridiom.budgetaudit.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BudgetAuditDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BudgetAudit budgetAudit = (BudgetAudit)incomingRequest.get("budgetAudit");
		if(budgetAudit == null)
		{
			budgetAudit = new BudgetAudit();
		}
		BudgetAuditSetValuesPK setValues = new BudgetAuditSetValuesPK();
		setValues.setValues(incomingRequest, budgetAudit);
		dbs.delete(budgetAudit);
		this.setStatus(dbs.getStatus()) ;
		return budgetAudit ;
	}

}