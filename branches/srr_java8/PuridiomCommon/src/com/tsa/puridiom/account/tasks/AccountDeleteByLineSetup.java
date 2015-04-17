/**
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AccountDeleteByLineSetup extends Task
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
			String formType = (String) incomingRequest.get("Account_accountType");
			String accountType = "H";
			String referenceType = "";

			if (HiltonUtility.ckNull(formType).length() == 3)
			{
				accountType = formType.substring(2);
				referenceType = formType.substring(0, 2);
			}

			incomingRequest.put("accountType", accountType);
			incomingRequest.put("referenceType", referenceType);
			
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupType error " + e.getMessage());

			throw e;
		}

		return result;
	}

}