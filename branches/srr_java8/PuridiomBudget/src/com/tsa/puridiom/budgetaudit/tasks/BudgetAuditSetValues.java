package com.tsa.puridiom.budgetaudit.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetAuditSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetAudit budgetAudit = (BudgetAudit) incomingRequest.get("budgetAudit");
			if (budgetAudit == null)
			{
				budgetAudit = new BudgetAudit();
			}

			if (incomingRequest.containsKey("BudgetAudit_auditId"))
			{
				String auditIdString = (String) incomingRequest.get("BudgetAudit_auditId");
				if (Utility.isEmpty(auditIdString))
				{
					auditIdString = "0";
				}
				BigDecimal auditId = new BigDecimal ( auditIdString );
				budgetAudit.setAuditId(auditId);
			}
			if (incomingRequest.containsKey("BudgetAudit_budgetId"))
			{
				String budgetIdString = (String) incomingRequest.get("BudgetAudit_budgetId");
				if (Utility.isEmpty(budgetIdString))
				{
					budgetIdString = "0";
				}
				BigDecimal budgetId = new BigDecimal ( budgetIdString );
				budgetAudit.setIcHeader(budgetId);
			}
			if (incomingRequest.containsKey("BudgetAudit_authority"))
			{
				String authority = (String) incomingRequest.get("BudgetAudit_authority");
				budgetAudit.setAuthority(authority);
			}
			if (incomingRequest.containsKey("BudgetAudit_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("BudgetAudit_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				budgetAudit.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("BudgetAudit_icLine"))
			{
				String icLineString = (String) incomingRequest.get("BudgetAudit_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				budgetAudit.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("BudgetAudit_actionCode"))
			{
				String actionCode = (String) incomingRequest.get("BudgetAudit_actionCode");
				budgetAudit.setActionCode(actionCode);
			}
			if (incomingRequest.containsKey("BudgetAudit_actionType"))
			{
				String actionType = (String) incomingRequest.get("BudgetAudit_actionType");
				budgetAudit.setActionType(actionType);
			}
			if (incomingRequest.containsKey("BudgetAudit_actionMake"))
			{
				String actionMake = (String) incomingRequest.get("BudgetAudit_actionMake");
				budgetAudit.setActionMake(actionMake);
			}
			if (incomingRequest.containsKey("BudgetAudit_actionDate"))
			{
				String actionDate = (String) incomingRequest.get("BudgetAudit_actionDate");
				budgetAudit.setActionDate(actionDate);
			}
			if (incomingRequest.containsKey("BudgetAudit_actionTime"))
			{
				String actionTime = (String) incomingRequest.get("BudgetAudit_actionTime");
				budgetAudit.setActionTime(actionTime);
			}
			if (incomingRequest.containsKey("BudgetAudit_priorAmt"))
			{
				String priorAmtString = (String) incomingRequest.get("BudgetAudit_priorAmt");
				if (Utility.isEmpty(priorAmtString))
				{
					priorAmtString = "0";
				}
				BigDecimal priorAmt = new BigDecimal ( priorAmtString );
				budgetAudit.setPriorAmt(priorAmt);
			}
			if (incomingRequest.containsKey("BudgetAudit_tranAmt"))
			{
				String tranAmtString = (String) incomingRequest.get("BudgetAudit_tranAmt");
				if (Utility.isEmpty(tranAmtString))
				{
					tranAmtString = "0";
				}
				BigDecimal tranAmt = new BigDecimal ( tranAmtString );
				budgetAudit.setTranAmt(tranAmt);
			}
			if (incomingRequest.containsKey("BudgetAudit_balance"))
			{
				String balanceString = (String) incomingRequest.get("BudgetAudit_balance");
				if (Utility.isEmpty(balanceString))
				{
					balanceString = "0";
				}
				BigDecimal balance = new BigDecimal ( balanceString );
				budgetAudit.setTranAmt(balance);
			}
			if (incomingRequest.containsKey("BudgetAudit_parentType"))
			{
				String parentType = (String) incomingRequest.get("BudgetAudit_parentType");
				budgetAudit.setParentType(parentType);
			}
			if (incomingRequest.containsKey("BudgetAudit_lineType"))
			{
				String lineType = (String) incomingRequest.get("BudgetAudit_lineType");
				budgetAudit.setLineType(lineType);
			}
			if (incomingRequest.containsKey("BudgetAudit_owner"))
			{
				String owner = (String) incomingRequest.get("BudgetAudit_owner");
				budgetAudit.setOwner(owner);
			}
			if (incomingRequest.containsKey("BudgetAudit_budgetFlag"))
			{
				String budgetFlag = (String) incomingRequest.get("BudgetAudit_budgetFlag");
				budgetAudit.setBudgetFlag(budgetFlag);
			}
			if (incomingRequest.containsKey("BudgetAudit_exportCode"))
			{
				String exportCode = (String) incomingRequest.get("BudgetAudit_exportCode");
				budgetAudit.setExportCode(exportCode);
			}
			if (incomingRequest.containsKey("BudgetAudit_exportDate"))
			{
				String exportDate = (String) incomingRequest.get("BudgetAudit_exportDate");
				budgetAudit.setExportDate(exportDate);
			}
			if (incomingRequest.containsKey("BudgetAudit_exportGroup"))
			{
				String exportGroup = (String) incomingRequest.get("BudgetAudit_exportGroup");
				budgetAudit.setExportGroup(exportGroup);
			}

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