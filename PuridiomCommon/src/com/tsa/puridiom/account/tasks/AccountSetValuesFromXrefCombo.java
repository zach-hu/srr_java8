/**
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.XrefCombo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AccountSetValuesFromXrefCombo extends Task
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
			XrefCombo xrefCombo = (XrefCombo) incomingRequest.get("xrefComboAccount");

			if (xrefCombo != null)
			{
				incomingRequest.put("Account_fld2", xrefCombo.getCode4());
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AccountSetValuesFromXrefCombo error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}