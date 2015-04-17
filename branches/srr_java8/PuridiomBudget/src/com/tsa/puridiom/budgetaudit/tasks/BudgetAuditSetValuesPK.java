package com.tsa.puridiom.budgetaudit.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetAuditSetValuesPK
{
	public void setValues(Map incomingRequest, BudgetAudit budgetaudit ) throws Exception
	{
		try
		{
			BigDecimal auditId = new BigDecimal((String ) incomingRequest.get("BudgetAudit_auditId"));
			budgetaudit.setAuditId(auditId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}