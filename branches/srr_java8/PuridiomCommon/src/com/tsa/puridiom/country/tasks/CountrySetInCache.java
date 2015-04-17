/*
 * Created on September, 2006
 */
package com.tsa.puridiom.country.tasks;

import com.tsa.puridiom.entity.Country;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class CountrySetInCache extends Task
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
			Country country = (Country) incomingRequest.get("country");
			
			if (country != null)
			{
				CurrencyManager.getInstance(organizationId).setCountryInCache(country);
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
