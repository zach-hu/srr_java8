/**
 * 
 * Created on Feb 15, 2005
 * @author Kelli
 * com.tsa.puridiom.account.tasks.ItemAccountRetrieveSetup.java
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ItemAccountRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String accountIc = (String) incomingRequest.get("itemAccount_icHeader");

			incomingRequest.put("Account_icHeader", accountIc);
			
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
