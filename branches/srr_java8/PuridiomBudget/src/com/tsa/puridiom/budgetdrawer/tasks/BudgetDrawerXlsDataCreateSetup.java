/**
 * 
 */
package com.tsa.puridiom.budgetdrawer.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class BudgetDrawerXlsDataCreateSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			String language = (String) incomingRequest.get("language");
			String authorityudf = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "AUTHORITY_UDF", "");

			if (!HiltonUtility.isEmpty((String) incomingRequest.get("BudgetDrawer_authType")))
			{
				String budgetDrawerType = (String) incomingRequest.get("BudgetDrawer_authType");
				String typeOption_1 = DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "budgetAuthorityDepartment", "Department");
				String typeOption_2 = DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "requisitioner", "Requisitioner");
				String typeOption_3 = "Account UDF";

				if (budgetDrawerType.equalsIgnoreCase(typeOption_1))
				{
					budgetDrawerType = "Department";
				} else if (budgetDrawerType.equalsIgnoreCase(typeOption_2))
				{
					budgetDrawerType = "Requisitioner";
				} else if (budgetDrawerType.equalsIgnoreCase(typeOption_3))
				{
					budgetDrawerType = authorityudf;
				}
				
				incomingRequest.put("BudgetDrawer_authType", budgetDrawerType);
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetDrawer_status")))
			{
				incomingRequest.put("BudgetDrawer_status", "01");
			}

			if (HiltonUtility.isEmpty((String) incomingRequest.get("BudgetDrawer_owner")))
			{
				incomingRequest.put("BudgetDrawer_owner", userId);
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetDrawerXlsDataCreateSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}
}