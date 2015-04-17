/**
 * 
 * Created on Feb 15, 2005
 * @author kathleen
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationAccountSetup.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationAccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String deleteall = (String) incomingRequest.get("deleteall"); 
			if (deleteall.equalsIgnoreCase("TRUE"))
			{
				incomingRequest.put("InvLocation_icInvAccount", "0");
			}
			else
			{
				String accountIc = (String) incomingRequest.get("Account_icHeader");
				incomingRequest.put("InvLocation_icInvAccount", accountIc);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
