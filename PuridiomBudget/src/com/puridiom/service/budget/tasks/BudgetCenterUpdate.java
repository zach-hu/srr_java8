/*
 * Created on April 30, 2008
 */
package com.puridiom.service.budget.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetAudit;
import com.tsa.puridiom.entity.BudgetCenter;
import com.puridiom.service.budget.BudgetWrapper;
import com.tsa.puridiom.budgetaudit.tasks.BudgetAuditAdd;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Renzo
 */
public class BudgetCenterUpdate extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BudgetWrapper budgetWrapper[] = (BudgetWrapper[])incomingRequest.get("budgetCenter");
			String budgetAction[] = (String[])incomingRequest.get("budgetAction") ;
			BigDecimal amount[] = (BigDecimal[])incomingRequest.get("budgetAmount");
			Map updateArgs = (Map)incomingRequest.get("updateArgs");
			BigDecimal icHeader = (BigDecimal)updateArgs.get("headerIc");
			BigDecimal icLine[] = (BigDecimal[])updateArgs.get("lineIc");
			String formType = (String)updateArgs.get("formType");
			String organizationId = (String)incomingRequest.get("organizationId");
			Object dbsession = incomingRequest.get("dbsession");

			for (int i = 0; i < budgetWrapper.length; i++)
			{
				if(budgetWrapper[i] != null)
				{
					Map updateRequest = new HashMap();
					BudgetCenter budgetCenter = (BudgetCenter)budgetWrapper[i].getBudgetCenter();
					updateRequest.put("BudgetCenter_budgetId", budgetCenter.getBudgetId());
					updateRequest.put("budgetCenter", budgetCenter);
					updateRequest.put("dbsession", dbsession);
					updateRequest.put("organizationId", organizationId);
					updateRequest.put("budgetAction", budgetAction[i]);
					updateRequest.put("budgetAmount", amount[i]);
					updateRequest.put("userId", budgetWrapper[i].getLockingId());
					//Arguments used for BudgetAudit Update
					updateRequest.put("icHeader", icHeader);
					updateRequest.put("icLine", icLine[i]);
					updateRequest.put("formType", formType);
					updateRequest.put("auditIc", ukg.getUniqueKey().toString());

					Log.info(this, "Updating: " + budgetWrapper[i].getKeyAsString() +
							", budgetAction: " + budgetAction[i] +
							", budgetAmount: " + amount[i] +
							", userId: " + budgetWrapper[i].getLockingId() +
							", icHeader: " + icHeader +
							", icLine: " + icLine[i] +
							", formType: " + formType
							);
					int status = updateBudgetCenter(updateRequest, budgetWrapper[i]);

					if(status == Status.SUCCEEDED)
					{
						BudgetAudit budgetAudit = this.createAudit(updateRequest);
						if(budgetAudit != null)
						{
							updateRequest.put("budgetAudit", budgetAudit);
							int auditResult = this.updateAuditRecord(updateRequest);
							this.setStatus(auditResult);
						}
						else
						{
							this.setStatus(Status.FAILED);
						}
					}
				}
				else
				{
					this.setStatus(Status.SUCCEEDED);
				}
			}
			result = budgetWrapper;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

	public int updateAuditRecord(Map updateRequest)
	{
		BudgetAuditAdd budgetAuditAdd = new BudgetAuditAdd();
		try
		{
			budgetAuditAdd.executeTask(updateRequest);
		}
		catch (Exception e)
		{
			budgetAuditAdd.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		return budgetAuditAdd.getStatus();
	}
	public BudgetAudit createAudit(Map updateRequest)
	{
		BudgetAuditCreateFromService auditCreate = new BudgetAuditCreateFromService();
		BudgetAudit budgetAudit = null;
		try
		{
			budgetAudit = (BudgetAudit)auditCreate.executeTask(updateRequest);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return budgetAudit;
	}

	public int updateBudgetCenter(Map updateRequest, BudgetWrapper budgetWrapper)
	{
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		BigDecimal tranIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
		updateRequest.put("tranIc", tranIc.toString());

		BudgetUpdateFromService updateBudgetCenter = new BudgetUpdateFromService();
		 BudgetCenter budgetCenterTemp = null;
		try {
			budgetCenterTemp = (BudgetCenter)updateBudgetCenter.executeTask(updateRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int updateStatus = updateBudgetCenter.getStatus();
		 if(updateStatus != Status.SUCCEEDED)
		 {
			 this.setStatus(Status.FAILED);
			 return Status.FAILED;
		 }
		 else
		 {
			 budgetWrapper.setBudgetCenter(budgetCenterTemp);
		 }
		 return Status.SUCCEEDED;
	}
}
