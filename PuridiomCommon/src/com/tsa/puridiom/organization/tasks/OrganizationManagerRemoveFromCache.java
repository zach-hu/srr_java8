/*
 * Created on April 24, 2009
 */
package com.tsa.puridiom.organization.tasks;

import com.tsa.puridiom.organization.OrganizationManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class OrganizationManagerRemoveFromCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	organizationId = (String) incomingRequest.get("Organization_organizationId");

			OrganizationManager.getInstance().removeOrganizationFromCache(organizationId);

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}

}
