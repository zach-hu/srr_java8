/*
 * Created on March, 2004
 */
package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsa.puridiom.supplierportal.BidBoardUserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class SetBidBoardUserInCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			
			if (user != null)
			{
				BidBoardUserManager.getInstance().setUserInCache(user);
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
