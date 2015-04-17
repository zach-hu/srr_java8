package com.tsa.puridiom.budgetdrawer.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetDrawerSetValuesPK
{
	public void setValues(Map incomingRequest, BudgetDrawer budgetdrawer ) throws Exception
	{
		try
		{
			String budgetId = (String ) incomingRequest.get("BudgetDrawer_budgetId");
			String authType = (String ) incomingRequest.get("BudgetDrawer_authType");
			String authority = (String ) incomingRequest.get("BudgetDrawer_authority");
			BudgetDrawerPK comp_id = new BudgetDrawerPK();
			comp_id.setAuthority(authority);
			comp_id.setAuthType(authType);
			comp_id.setBudgetId(budgetId);
			budgetdrawer.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}