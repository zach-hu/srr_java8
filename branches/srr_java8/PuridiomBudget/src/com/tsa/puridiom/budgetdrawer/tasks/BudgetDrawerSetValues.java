package com.tsa.puridiom.budgetdrawer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BudgetDrawerSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetDrawerPK comp_id = new BudgetDrawerPK();
			BudgetDrawer budgetDrawer = (BudgetDrawer) incomingRequest.get("budgetDrawer");
			if (budgetDrawer == null)
			{
				budgetDrawer = new BudgetDrawer();
			}

			if (incomingRequest.containsKey("BudgetDrawer_budgetId"))
			{
				String budgetId = (String) incomingRequest.get("BudgetDrawer_budgetId");
				comp_id.setBudgetId(budgetId);
			}
			if (incomingRequest.containsKey("BudgetDrawer_authType"))
			{
				String authType = (String) incomingRequest.get("BudgetDrawer_authType");
				comp_id.setAuthType(authType.substring(0,1));
			}
			if (incomingRequest.containsKey("BudgetDrawer_authority"))
			{
				String authority = (String) incomingRequest.get("BudgetDrawer_authority");
				comp_id.setAuthority(authority);
			}
			if (incomingRequest.containsKey("BudgetDrawer_owner"))
			{
				String owner = (String) incomingRequest.get("BudgetDrawer_owner");
				budgetDrawer.setOwner(owner);
			}
			if (incomingRequest.containsKey("BudgetDrawer_status"))
			{
				String status = (String) incomingRequest.get("BudgetDrawer_status");
				budgetDrawer.setStatus(status);
			}
			if (incomingRequest.containsKey("BudgetDrawer_budgetFlag"))
			{
				String budgetFlag = (String) incomingRequest.get("BudgetDrawer_budgetFlag");
				budgetDrawer.setBudgetFlag(budgetFlag);
			}
			budgetDrawer.setComp_id(comp_id);
			result = budgetDrawer;
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