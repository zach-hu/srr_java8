package com.tsa.puridiom.budgettran.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetTranSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetTran budgetTran = (BudgetTran) incomingRequest.get("budgetTran");
			if (budgetTran == null)
			{
				budgetTran = new BudgetTran();
			}

			if (incomingRequest.containsKey("BudgetTran_tranId"))
			{
				String tranIdString = (String) incomingRequest.get("BudgetTran_tranId");
				if (Utility.isEmpty(tranIdString))
				{
					tranIdString = "0";
				}
				BigDecimal tranId = new BigDecimal ( tranIdString );
				budgetTran.setTranId(tranId);
			}
			if (incomingRequest.containsKey("BudgetTran_auditId"))
			{
				String auditIdString = (String) incomingRequest.get("BudgetTran_auditId");
				if (Utility.isEmpty(auditIdString))
				{
					auditIdString = "0";
				}
				BigDecimal auditId = new BigDecimal ( auditIdString );
				budgetTran.setAuditId(auditId);
			}
			if (incomingRequest.containsKey("BudgetTran_budgetId"))
			{
				String budgetIdString = (String) incomingRequest.get("BudgetTran_budgetId");
				if (Utility.isEmpty(budgetIdString))
				{
					budgetIdString = "0";
				}
				BigDecimal budgetId = new BigDecimal ( budgetIdString );
				budgetTran.setBudgetId(budgetId);
			}
			if (incomingRequest.containsKey("BudgetTran_tranType"))
			{
				String tranType = (String) incomingRequest.get("BudgetTran_tranType");
				budgetTran.setTranType(tranType);
			}
			if (incomingRequest.containsKey("BudgetTran_tranDate"))
			{
				String tranDateString = (String) incomingRequest.get("BudgetTran_tranDate");
				Date tranDate = Dates.getDate(tranDateString);
				budgetTran.setTranDate(tranDate);
			}
			if (incomingRequest.containsKey("BudgetTran_tranTime"))
			{
				String tranTime = (String) incomingRequest.get("BudgetTran_tranTime");
				budgetTran.setTranTime(tranTime);
			}
			if (incomingRequest.containsKey("BudgetTran_preEncumbered"))
			{
				String preEncumberedString = (String) incomingRequest.get("BudgetTran_preEncumbered");
				if (Utility.isEmpty(preEncumberedString))
				{
					preEncumberedString = "0";
				}
				BigDecimal preEncumbered = new BigDecimal ( preEncumberedString );
				budgetTran.setPreEncumbered(preEncumbered);
			}
			if (incomingRequest.containsKey("BudgetTran_encumbered"))
			{
				String encumberedString = (String) incomingRequest.get("BudgetTran_encumbered");
				if (Utility.isEmpty(encumberedString))
				{
					encumberedString = "0";
				}
				BigDecimal encumbered = new BigDecimal ( encumberedString );
				budgetTran.setEncumbered(encumbered);
			}
			if (incomingRequest.containsKey("BudgetTran_expensed"))
			{
				String expensedString = (String) incomingRequest.get("BudgetTran_expensed");
				if (Utility.isEmpty(expensedString))
				{
					expensedString = "0";
				}
				BigDecimal expensed = new BigDecimal ( expensedString );
				budgetTran.setExpensed(expensed);
			}
			if (incomingRequest.containsKey("BudgetTran_balance"))
			{
				String balanceString = (String) incomingRequest.get("BudgetTran_balance");
				if (Utility.isEmpty(balanceString))
				{
					balanceString = "0";
				}
				BigDecimal balance = new BigDecimal ( balanceString );
				budgetTran.setBalance(balance);
			}
			if (incomingRequest.containsKey("BudgetTran_owner"))
			{
				String owner = (String) incomingRequest.get("BudgetTran_owner");
				budgetTran.setOwner(owner);
			}

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