package com.tsa.puridiom.budgettran.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BudgetTranSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain budgetTran */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BudgetTran	originalBudgetTran = (BudgetTran) incomingRequest.get("budgetTran");
			BudgetTran	budgetTran = new BudgetTran();

			budgetTran.setTranId(originalBudgetTran.getTranId());
			budgetTran.setAuditId(originalBudgetTran.getAuditId());
			budgetTran.setBudgetId(originalBudgetTran.getBudgetId());
			budgetTran.setTranType(originalBudgetTran.getTranType());
			budgetTran.setTranDate(originalBudgetTran.getTranDate());
			budgetTran.setTranTime(originalBudgetTran.getTranTime());
			budgetTran.setPreEncumbered(originalBudgetTran.getPreEncumbered());
			budgetTran.setEncumbered(originalBudgetTran.getEncumbered());
			budgetTran.setExpensed(originalBudgetTran.getExpensed());
			budgetTran.setBalance(originalBudgetTran.getBalance());
			budgetTran.setOwner(originalBudgetTran.getOwner());

			incomingRequest.put("budgetTran", budgetTran);

			BudgetTranAdd addTask = new BudgetTranAdd();
			budgetTran = (BudgetTran) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = budgetTran;
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