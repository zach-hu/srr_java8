/*
 * Created on April 30, 2008
 */
package com.puridiom.service.budget.tasks;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.property.PropertiesManager;
import com.puridiom.service.budget.BudgetAction;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Renzo
 */
public class BudgetUpdateFromService extends Task
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
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			BudgetCenter budgetCenter = (BudgetCenter)incomingRequest.get("budgetCenter");
			String organizationId = (String)incomingRequest.get("organizationId");
			String budgetAction = (String)incomingRequest.get("budgetAction") ;
			BigDecimal amount = (BigDecimal)incomingRequest.get("budgetAmount");
			budgetCenter = this.getBudgetColumn(budgetAction, amount, budgetCenter, incomingRequest, organizationId);

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String userId = (String) incomingRequest.get("userId");
			BigDecimal tranIc = new BigDecimal(ukg.getUniqueKey().toString()) ;
			BigDecimal auditIc = new BigDecimal(incomingRequest.get("auditIc").toString()) ;

			budgetCenter.setLastChangeBy(userId) ;
			budgetCenter.setLastChangeDate(new java.util.Date()) ;
			budgetCenter.setLastTranId(tranIc) ;
			budgetCenter.setLastAuditId(auditIc) ;
			dbs.getSession().merge(budgetCenter) ;

			//dbs.getSession().flush() ;
			result = budgetCenter;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{

			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

	public BudgetCenter getBudgetColumn(String budgetAction, BigDecimal amount, BudgetCenter budgetCenter, Map incomingRequest, String organizationId)
	{
		int action = Integer.parseInt(budgetAction);
		if(action == BudgetAction.ENCUMBRANCE)
		{
			String updateAction = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "ENCUMBERENCE", "Y");
			if(updateAction.equalsIgnoreCase("Y"))
			{
				budgetCenter.setEncumbered(budgetCenter.getEncumbered().add(amount));
			}
			else
			{
				incomingRequest.put("budgetAmount", new BigDecimal("0"));
				Log.info(this, "Encunbrance Actions are not logged");
			}
		}
		else if(action == BudgetAction.EXPENSED)
		{
			String updateAction = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "EXPENSE", "Y");
			if(updateAction.equalsIgnoreCase("Y"))
			{
				budgetCenter.setExpensed(budgetCenter.getExpensed().add(amount));
			}
			else
			{
				incomingRequest.put("budgetAmount", new BigDecimal("0"));
				Log.info(this, "EXPENSE Actions are not logged");
			}

		}
		else if(action == BudgetAction.PREENCUMBRANCE)
		{
			String updateAction = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "PREENCUMBERENCE", "Y");
			if(updateAction.equalsIgnoreCase("Y"))
			{
				budgetCenter.setPreEncumbered(budgetCenter.getPreEncumbered().add(amount));
			}
			else
			{
				incomingRequest.put("budgetAmount", new BigDecimal("0"));
				Log.info(this, "PREENCUMBERENCE Actions are not logged");
			}
		}
		return budgetCenter;
	}
}
