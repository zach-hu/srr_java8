/*
 * Created on May 18, 2011
 */
package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsa.puridiom.supplierportal.BidBoardUserManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author matthewd
 */
public class BidBoardUserGetMailIdLoginAttempts extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");
			Integer attempts = new Integer(0);

			if (!HiltonUtility.isEmpty(organizationId))
			{
				attempts = new Integer(BidBoardUserManager.getInstance().getFailedLoginAttempts(organizationId, userId));
			}

			result = attempts;
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return result;
		}
	}

}
