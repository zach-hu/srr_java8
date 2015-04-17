/**
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class ItemAccountSetIcAccount extends Task
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
			String formtype = (String) incomingRequest.get("formtype");
			if (formtype.equalsIgnoreCase("REQ"))
			{
				incomingRequest.put("RequisitionLine_icAccount", incomingRequest.get("Account_icLine"));
				
			} else if (formtype.equalsIgnoreCase("PO"))
			{
				incomingRequest.put("PoLine_icAccount", incomingRequest.get("Account_icLine"));
				
			} else if (formtype.equalsIgnoreCase("DSB"))
			{
				incomingRequest.put("DisbLine_icAccount", incomingRequest.get("Account_icLine"));
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "ItemAccountSetIcAccount error " + e.getMessage());

			throw e;
		}

		return result;
	}

}