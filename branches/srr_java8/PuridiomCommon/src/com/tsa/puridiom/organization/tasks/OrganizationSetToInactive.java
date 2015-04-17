/*
 * Created on June 10, 2004
 */
package com.tsa.puridiom.organization.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;

import com.tsa.puridiom.entity.Organization;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class OrganizationSetToInactive extends Task
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
			Organization organization = (Organization) incomingRequest.get("organization");
			if (organization != null) {
				organization.setStatus(GeneralStatus.STATUS_INACTIVE);
			}

			result = organization;
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
