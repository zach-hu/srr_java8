/*
 * Created on September, 2006
 */
package com.tsa.puridiom.country.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class CountryRemoveFromCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String countryCode = (String) incomingRequest.get("Country_countryCode");
			
			if (!Utility.isEmpty(countryCode))
			{
				CurrencyManager.getInstance(organizationId).removeCountryFromCache(countryCode);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return incomingRequest;
		}
	}

}
