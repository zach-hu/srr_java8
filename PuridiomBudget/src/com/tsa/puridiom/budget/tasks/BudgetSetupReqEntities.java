/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.puridiom.service.budget.BudgetAction;
import com.puridiom.service.budget.BudgetEntity;
import com.puridiom.service.budget.BudgetUtils;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetSetupReqEntities extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineItems");
			List budgetActions = (List) incomingRequest.get("budgetServiceActions");
			String organizationId = (String) incomingRequest.get("organizationId");
			List budgetEntities = new ArrayList();

			incomingRequest.put("budgetIcHeader", reqHeader.getIcReqHeader());
			incomingRequest.put("budgetServiceYear", reqHeader.getFiscalYear());
			incomingRequest.put("budgetCurrency", reqHeader.getCurrencyCode());

			for (Iterator iterator = budgetActions.iterator(); iterator.hasNext();)
			{
				BudgetAction budgetAction = (BudgetAction) iterator.next();

				for (int i = 0; i < lineItems.size(); i++)
				{
					Object lineItem = lineItems.get(i);

					RequisitionLine reqLine = (RequisitionLine) lineItem;
					String authority = this.getAuthority(organizationId, reqHeader, reqLine);

					budgetEntities.add(BudgetEntity.buildBudgetEntity(reqLine, budgetAction, authority));
				}
			}

			result = budgetEntities;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupReqEntities error " + e.getMessage());

			throw e;
		}

		return result;
	}

	private String getAuthority(String organizationId, RequisitionHeader reqHeader, RequisitionLine reqLine)
	{
		String authority = "";
		boolean isAuthRequisitioner = BudgetUtils.isAuthorityRequisitioner(organizationId);

		authority = (isAuthRequisitioner) ? reqLine.getRequisitionerCode() : reqLine.getDepartmentCode();

		if (HiltonUtility.isEmpty(authority))
		{
			authority = (isAuthRequisitioner) ? reqHeader.getRequisitionerCode() : reqHeader.getDepartmentCode();
		}

		return authority;
	}

}
