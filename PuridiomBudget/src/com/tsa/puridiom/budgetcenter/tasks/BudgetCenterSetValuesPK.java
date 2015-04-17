package com.tsa.puridiom.budgetcenter.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetCenterSetValuesPK
{
	public void setValues(Map incomingRequest, BudgetCenter budgetcenter ) throws Exception
	{
		try
		{
			BigDecimal budgetId = new BigDecimal((String ) incomingRequest.get("BudgetCenter_budgetId"));
			budgetcenter.setBudgetId(budgetId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}