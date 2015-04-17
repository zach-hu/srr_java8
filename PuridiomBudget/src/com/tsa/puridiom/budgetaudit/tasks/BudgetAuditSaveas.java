package com.tsa.puridiom.budgetaudit.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BudgetAuditSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain budgetAudit */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BudgetAudit	originalBudgetAudit = (BudgetAudit) incomingRequest.get("budgetAudit");
			BudgetAudit	budgetAudit = new BudgetAudit();

			budgetAudit.setAuditId(originalBudgetAudit.getAuditId());
			budgetAudit.setBudgetId(originalBudgetAudit.getBudgetId());
			budgetAudit.setAuthority(originalBudgetAudit.getAuthority());
			budgetAudit.setIcHeader(originalBudgetAudit.getIcHeader());
			budgetAudit.setIcLine(originalBudgetAudit.getIcLine());
			budgetAudit.setActionCode(originalBudgetAudit.getActionCode());
			budgetAudit.setActionType(originalBudgetAudit.getActionType());
			budgetAudit.setActionMake(originalBudgetAudit.getActionMake());
			budgetAudit.setActionDate(originalBudgetAudit.getActionDate());
			budgetAudit.setActionTime(originalBudgetAudit.getActionTime());
			budgetAudit.setPriorAmt(originalBudgetAudit.getPriorAmt());
			budgetAudit.setTranAmt(originalBudgetAudit.getTranAmt());
			budgetAudit.setBalance(originalBudgetAudit.getBalance());
			budgetAudit.setParentType(originalBudgetAudit.getParentType());
			budgetAudit.setLineType(originalBudgetAudit.getLineType());
			budgetAudit.setOwner(originalBudgetAudit.getOwner());
			budgetAudit.setBudgetFlag(originalBudgetAudit.getBudgetFlag());
			budgetAudit.setExportCode(originalBudgetAudit.getExportCode());
			budgetAudit.setExportDate(originalBudgetAudit.getExportDate());
			budgetAudit.setExportGroup(originalBudgetAudit.getExportGroup());

			incomingRequest.put("budgetAudit", budgetAudit);

			BudgetAuditAdd addTask = new BudgetAuditAdd();
			budgetAudit = (BudgetAudit) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = budgetAudit;
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