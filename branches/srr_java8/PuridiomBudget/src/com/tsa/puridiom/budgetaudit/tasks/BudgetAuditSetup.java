package com.tsa.puridiom.budgetaudit.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BudgetAuditSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BudgetAudit_auditId", "");
			incomingRequest.put("BudgetAudit_prevAuditId", "");
			incomingRequest.put("BudgetAudit_budgetId", "");
			incomingRequest.put("BudgetAudit_authority", "");
			incomingRequest.put("BudgetAudit_icHeader", "0");
			incomingRequest.put("BudgetAudit_icLine", "0");
			incomingRequest.put("BudgetAudit_actionCode", "");
			incomingRequest.put("BudgetAudit_actionType", "");
			incomingRequest.put("BudgetAudit_actionMake", "");
			incomingRequest.put("BudgetAudit_actionDate", "");
			incomingRequest.put("BudgetAudit_actionTime", "");
			incomingRequest.put("BudgetAudit_priorAmt", "0");
			incomingRequest.put("BudgetAudit_tranAmt", "0");
			incomingRequest.put("BudgetAudit_balance", "0");
			incomingRequest.put("BudgetAudit_parentType", "");
			incomingRequest.put("BudgetAudit_lineType", "");
			incomingRequest.put("BudgetAudit_owner", "");
			incomingRequest.put("BudgetAudit_budgetFlag", "");
			incomingRequest.put("BudgetAudit_exportCode", "");
			incomingRequest.put("BudgetAudit_exportDate", "");
			incomingRequest.put("BudgetAudit_exportGroup", "");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}