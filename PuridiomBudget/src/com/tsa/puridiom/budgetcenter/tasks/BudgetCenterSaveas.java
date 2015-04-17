package com.tsa.puridiom.budgetcenter.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BudgetCenterSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			/* Expects incoming request to contain budgetCenter */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BudgetCenter	originalBudgetCenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			BudgetCenter	budgetCenter = new BudgetCenter();

			budgetCenter.setBudgetId(originalBudgetCenter.getBudgetId());
			budgetCenter.setBudgetPeriod(originalBudgetCenter.getBudgetPeriod());
			budgetCenter.setBudget1(originalBudgetCenter.getBudget1());
			budgetCenter.setBudget2(originalBudgetCenter.getBudget2());
			budgetCenter.setBudget3(originalBudgetCenter.getBudget3());
			budgetCenter.setBudget4(originalBudgetCenter.getBudget4());
			budgetCenter.setBudget5(originalBudgetCenter.getBudget5());
			budgetCenter.setBudget6(originalBudgetCenter.getBudget6());
			budgetCenter.setBudget7(originalBudgetCenter.getBudget7());
			budgetCenter.setBudget8(originalBudgetCenter.getBudget8());
			budgetCenter.setBudget9(originalBudgetCenter.getBudget9());
			budgetCenter.setBudget10(originalBudgetCenter.getBudget10());
			budgetCenter.setBudget11(originalBudgetCenter.getBudget11());
			budgetCenter.setBudget12(originalBudgetCenter.getBudget12());
			budgetCenter.setBudget13(originalBudgetCenter.getBudget13());
			budgetCenter.setBudget14(originalBudgetCenter.getBudget14());
			budgetCenter.setBudget15(originalBudgetCenter.getBudget15());
			budgetCenter.setBudgeted(originalBudgetCenter.getBudgeted());
			budgetCenter.setPreEncumbered(originalBudgetCenter.getPreEncumbered());
			budgetCenter.setEncumbered(originalBudgetCenter.getEncumbered());
			budgetCenter.setExpensed(originalBudgetCenter.getExpensed());
			budgetCenter.setOwner(originalBudgetCenter.getOwner());
			budgetCenter.setOwnerPassword(originalBudgetCenter.getOwnerPassword());
			budgetCenter.setStatus(originalBudgetCenter.getStatus());
			budgetCenter.setApproved(originalBudgetCenter.getApproved());
			budgetCenter.setProjectId(originalBudgetCenter.getProjectId());
			budgetCenter.setComments(originalBudgetCenter.getComments());

			budgetCenter.setLastChangeBy(originalBudgetCenter.getOwner());
			budgetCenter.setLastChangeDate(com.tsagate.foundation.utility.Dates.getDateTimeHours(com.tsagate.foundation.utility.Dates.today("", userTimeZone)));
			budgetCenter.setLastTranId(new java.math.BigDecimal(0));
			budgetCenter.setLastAuditId(new java.math.BigDecimal(0));

			incomingRequest.put("budgetCenter", budgetCenter);

			BudgetCenterAdd addTask = new BudgetCenterAdd();
			budgetCenter = (BudgetCenter) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = budgetCenter;
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