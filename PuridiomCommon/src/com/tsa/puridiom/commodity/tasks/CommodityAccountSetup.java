/**
 * 
 * Created on Mar 11, 2005
 * @author kathleen
 * com.tsa.puridiom.commodity.tasks.CommodityItemAccountSetup.java
 * 
 */
package com.tsa.puridiom.commodity.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CommodityAccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String deleteall = (String) incomingRequest.get("deleteall"); 
			if (deleteall.equalsIgnoreCase("TRUE"))
			{
				incomingRequest.put("Commodity_icAccount", "0");
			}
			else
			{
				String accountIc = (String) incomingRequest.get("Account_icHeader");
				incomingRequest.put("Commodity_icAccount", accountIc);
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
