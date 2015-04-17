/**
 * 
 * Created on Feb 23, 2005
 * @author kathleen
 * com.tsa.puridiom.catalog.tasks.CatalogItemAccountSetup.java
 * 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CatalogAccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String deleteall = (String) incomingRequest.get("deleteall"); 
			if (deleteall.equalsIgnoreCase("TRUE"))
			{
				incomingRequest.put("Catalog_icAccount", "0");
			}
			else
			{
				String accountIc = (String) incomingRequest.get("Account_icHeader");
				incomingRequest.put("Catalog_icAccount", accountIc);
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
