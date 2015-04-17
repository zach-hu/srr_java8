package com.tsa.puridiom.budgetcenter.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class BudgetCenterSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetCenter budgetCenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			String organizationId = (String)incomingRequest.get("organizationId") ;
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			if (Utility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
	        }
			String date = Dates.today(userDateFormat, userTimeZone);

			if (budgetCenter == null)
			{
				budgetCenter = new BudgetCenter();
			}

			if (incomingRequest.containsKey("BudgetCenter_budgetId"))
			{
				String budgetIdString = (String) incomingRequest.get("BudgetCenter_budgetId");
				if (Utility.isEmpty(budgetIdString))
				{
					budgetIdString = "0";
				}
				BigDecimal budgetId = new BigDecimal ( budgetIdString );
				budgetCenter.setBudgetId(budgetId);
			}
			if (incomingRequest.containsKey("BudgetCenter_budgetPeriod"))
			{
				String budgetPeriod = (String) incomingRequest.get("BudgetCenter_budgetPeriod");
				budgetCenter.setBudgetPeriod(budgetPeriod);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget1"))
			{
				String budget1 = (String) incomingRequest.get("BudgetCenter_budget1");
				budgetCenter.setBudget1(budget1);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget2"))
			{
				String budget2 = (String) incomingRequest.get("BudgetCenter_budget2");
				budgetCenter.setBudget2(budget2);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget3"))
			{
				String budget3 = (String) incomingRequest.get("BudgetCenter_budget3");
				budgetCenter.setBudget3(budget3);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget4"))
			{
				String budget4 = (String) incomingRequest.get("BudgetCenter_budget4");
				budgetCenter.setBudget4(budget4);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget5"))
			{
				String budget5 = (String) incomingRequest.get("BudgetCenter_budget5");
				budgetCenter.setBudget5(budget5);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget6"))
			{
				String budget6 = (String) incomingRequest.get("BudgetCenter_budget6");
				budgetCenter.setBudget6(budget6);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget7"))
			{
				String budget7 = (String) incomingRequest.get("BudgetCenter_budget7");
				budgetCenter.setBudget7(budget7);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget8"))
			{
				String budget8 = (String) incomingRequest.get("BudgetCenter_budget8");
				budgetCenter.setBudget8(budget8);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget9"))
			{
				String budget9 = (String) incomingRequest.get("BudgetCenter_budget9");
				budgetCenter.setBudget9(budget9);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget10"))
			{
				String budget10 = (String) incomingRequest.get("BudgetCenter_budget10");
				budgetCenter.setBudget10(budget10);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget11"))
			{
				String budget11 = (String) incomingRequest.get("BudgetCenter_budget11");
				budgetCenter.setBudget11(budget11);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget12"))
			{
				String budget12 = (String) incomingRequest.get("BudgetCenter_budget12");
				budgetCenter.setBudget12(budget12);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget13"))
			{
				String budget13 = (String) incomingRequest.get("BudgetCenter_budget13");
				budgetCenter.setBudget13(budget13);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget14"))
			{
				String budget14 = (String) incomingRequest.get("BudgetCenter_budget14");
				budgetCenter.setBudget14(budget14);
			}
			if (incomingRequest.containsKey("BudgetCenter_budget15"))
			{
				String budget15 = (String) incomingRequest.get("BudgetCenter_budget15");
				budgetCenter.setBudget15(budget15);
			}
			if (incomingRequest.containsKey("BudgetCenter_budgeted"))
			{
				String budgetedString = (String) incomingRequest.get("BudgetCenter_budgeted");
				if (Utility.isEmpty(budgetedString))
				{
					budgetedString = "0";
				}
				BigDecimal budgeted = new BigDecimal ( budgetedString );
				budgetCenter.setBudgeted(budgeted);
			}
			if (incomingRequest.containsKey("BudgetCenter_preEncumbered"))
			{
				String preEncumberedString = (String) incomingRequest.get("BudgetCenter_preEncumbered");
				if (Utility.isEmpty(preEncumberedString))
				{
					preEncumberedString = "0";
				}
				BigDecimal preEncumbered = new BigDecimal ( preEncumberedString );
				budgetCenter.setPreEncumbered(preEncumbered);
			}
			if (incomingRequest.containsKey("BudgetCenter_encumbered"))
			{
				String encumberedString = (String) incomingRequest.get("BudgetCenter_encumbered");
				if (Utility.isEmpty(encumberedString))
				{
					encumberedString = "0";
				}
				BigDecimal encumbered = new BigDecimal ( encumberedString );
				budgetCenter.setEncumbered(encumbered);
			}
			if (incomingRequest.containsKey("BudgetCenter_expensed"))
			{
				String expensedString = (String) incomingRequest.get("BudgetCenter_expensed");
				if (Utility.isEmpty(expensedString))
				{
					expensedString = "0";
				}
				BigDecimal expensed = new BigDecimal ( expensedString );
				budgetCenter.setExpensed(expensed);
			}
			if (incomingRequest.containsKey("BudgetCenter_owner"))
			{
				String owner = (String) incomingRequest.get("BudgetCenter_owner");
				budgetCenter.setOwner(owner);
			}
			if (incomingRequest.containsKey("BudgetCenter_ownerPassword"))
			{
				String ownerPassword = (String) incomingRequest.get("BudgetCenter_ownerPassword");
				budgetCenter.setOwnerPassword(ownerPassword);
			}
			if (incomingRequest.containsKey("BudgetCenter_status"))
			{
				String status = (String) incomingRequest.get("BudgetCenter_status");
				budgetCenter.setStatus(status);
			}
			if (incomingRequest.containsKey("BudgetCenter_approved"))
			{
				String approved = (String) incomingRequest.get("BudgetCenter_approved");
				budgetCenter.setApproved(approved);
			}
			if (incomingRequest.containsKey("BudgetCenter_projectId"))
			{
				String projectId = (String) incomingRequest.get("BudgetCenter_projectId");
				budgetCenter.setProjectId(projectId);
			}
			if (incomingRequest.containsKey("BudgetCenter_comments"))
			{
				String comments = (String) incomingRequest.get("BudgetCenter_comments");
				budgetCenter.setComments(comments);
			}
			if (incomingRequest.containsKey("BudgetCenter_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("BudgetCenter_lastChangeBy");
				budgetCenter.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("BudgetTran_lastChangeDate"))
			{
				String lastChangeDateString = (String) incomingRequest.get("BudgetTran_lastChangeDate");
				Date lastChangeDate = Dates.getDate(lastChangeDateString);
				budgetCenter.setLastChangeDate(lastChangeDate);
			}
			if (incomingRequest.containsKey("BudgetCenter_lastTranId"))
			{
				String lastTranIdString = (String) incomingRequest.get("BudgetCenter_lastTranId");
				if (Utility.isEmpty(lastTranIdString))
				{
					lastTranIdString = "0";
				}
				BigDecimal lastTranId = new BigDecimal ( lastTranIdString );
				budgetCenter.setLastTranId(lastTranId);
			}

			if (incomingRequest.containsKey("BudgetCenter_lastAuditId"))
			{
				String lastAuditIdString = (String) incomingRequest.get("BudgetCenter_lastAuditId");
				if (Utility.isEmpty(lastAuditIdString))
				{
					lastAuditIdString = "0";
				}
				BigDecimal lastAuditId = new BigDecimal ( lastAuditIdString );
				budgetCenter.setLastAuditId(lastAuditId);
			}
			if (incomingRequest.containsKey("BudgetCenter_specificPeriod"))
			{
				String specificPeriod = (String) incomingRequest.get("BudgetCenter_specificPeriod");
				budgetCenter.setSpecificPeriod(specificPeriod);
			}
			if (incomingRequest.containsKey("BudgetCenter_amountIncrement"))
			{
				String amountString = (String) incomingRequest.get("BudgetCenter_amountIncrement");
				if (Utility.isEmpty(amountString))
				{
					amountString = "0";
				}
				BigDecimal amountIncrement = new BigDecimal ( amountString );
				budgetCenter.setAmountIncrement(amountIncrement);
			}
			if (incomingRequest.containsKey("BudgetCenter_amountIncrement"))
			{
				Date budgetIncrementDate = Dates.getDate(date);
				budgetCenter.setBudgetIncrementDate(budgetIncrementDate);
			}

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