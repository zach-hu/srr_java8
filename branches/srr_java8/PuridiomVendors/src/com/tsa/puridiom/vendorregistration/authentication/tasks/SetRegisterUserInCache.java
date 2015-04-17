/*
 * Created on July 25, 2006
 */
package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import java.util.Map;

/**
 * @author Kelli
 */
public class SetRegisterUserInCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			
			if (user != null)
			{
			    VendorRegistrationUserManager.getInstance().setUserInCache(user);
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
