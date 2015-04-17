/*
 * Created on September, 2006
 */
package com.tsa.puridiom.currcode.tasks;

import com.tsa.puridiom.entity.CurrCode;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class CurrCodeSetInCache extends Task
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
			CurrCode currCode = (CurrCode) incomingRequest.get("currCode");
			
			if (currCode != null)
			{
				CurrencyManager.getInstance(organizationId).setCurrCodeInCache(currCode);
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
