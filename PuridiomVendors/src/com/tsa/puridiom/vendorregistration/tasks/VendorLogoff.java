package com.tsa.puridiom.vendorregistration.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import java.util.*;

/**
 * Creation date: August 2004
 * @author: Kelli Knisely
 */
public class VendorLogoff extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		RegisterUser user = null;
		
		try 
		{
			String userId = (String) incomingRequest.get("userId");
			String	oid = (String) incomingRequest.get("organizationId");
			
			user = VendorRegistrationUserManager.getInstance().getUserInCache(oid, userId);
			
			user.setAuthenticated(false);
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return user;
		}
	}
}