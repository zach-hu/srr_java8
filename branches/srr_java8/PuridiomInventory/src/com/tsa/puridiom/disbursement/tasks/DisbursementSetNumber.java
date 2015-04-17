/*
 * Created on Dec 8, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventorySetDisbursementNumber.java
 */
 
package com.tsa.puridiom.disbursement.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbursementSetNumber extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DisbHeader disb = (DisbHeader)incomingRequest.get("disbHeader");
			String disbNumber = (String)incomingRequest.get("DisbHeader_disbNumber");
			disb.setDisbNumber(disbNumber);
			ret = disb;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
